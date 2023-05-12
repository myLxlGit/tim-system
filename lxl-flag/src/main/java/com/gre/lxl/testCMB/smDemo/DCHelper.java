package com.gre.lxl.testCMB.smDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DCHelper {

    public static String serialJsonOrdered(JsonObject json) throws Exception {
        StringBuilder appender = new StringBuilder();
        appender.append("{");
        Iterator<String> keys = new TreeSet<>(json.keySet()).iterator();
        boolean isFirstEle = true;
        while (keys.hasNext()) {
            if (!isFirstEle) {
                appender.append(",");
            }
            String key = keys.next();
            Object val = json.get(key);
            if (val instanceof JsonObject) {
                appender.append("\"").append(key).append("\":");
                appender.append(serialJsonOrdered((JsonObject) val));
            } else if (val instanceof JsonArray) {
                JsonArray jarray = (JsonArray) val;
                appender.append("\"").append(key).append("\":[");
                boolean isFirstArrEle = true;
                for (int i = 0; i < jarray.size(); i++) {
                    if (!isFirstArrEle) {
                        appender.append(",");
                    }
                    Object obj = jarray.get(i);
                    if (obj instanceof JsonObject) {
                        appender.append(serialJsonOrdered((JsonObject) obj));
                    } else {
                        appender.append(obj.toString());
                    }
                    isFirstArrEle = false;
                }
                appender.append("]");
            } else {
                String value = val.toString();
                appender.append("\"").append(key).append("\":").append(value);
            }
            isFirstEle = false;
        }
        appender.append("}");
        return appender.toString();
    }

    public static String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    // 发送Post请求
    public static String doPostForm(String httpUrl, HashMap<String, String> param) throws Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            SSLContext sslcontext;
            sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
            sslcontext.init(null, new TrustManager[] { new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            } }, new java.security.SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslsession) {
                    System.out.println("WARNING: Hostname is not matched for cert.");
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());

            // 新建连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置为POST请求
            connection.setRequestMethod("POST");
            // 设置连接超时时长
            connection.setConnectTimeout(15000);
            // 设置读取超时
            connection.setReadTimeout(60000);
            connection.setInstanceFollowRedirects(true);

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            os = connection.getOutputStream();
            os.write(createLinkString(param).getBytes());
            if (connection.getResponseCode() != 200) {
                is = connection.getErrorStream();
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringBuilder sbf = new StringBuilder();
                String temp = null;
                // 获取错误返回
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            } else {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringBuilder sbf = new StringBuilder();
                String temp = null;
                // 获取返回
                boolean firstLine = true;
                while ((temp = br.readLine()) != null) {
                    if (!firstLine) {
                        firstLine = false;
                        sbf.append("\r\n");
                    }
                    sbf.append(temp);
                }
                result = sbf.toString();
            }
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 关闭连接
            connection.disconnect();
        }
        return result;
    }

    private static String createLinkString(Map<String, String> params) throws Exception {
        ArrayList<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append("&");
            }
        }
        return prestr.toString();
    }

    public static String serialJsonOrdered(JSONObject json) {
        StringBuilder appender = new StringBuilder();
        appender.append("{");
        Iterator<String> keys = new TreeSet<>(json.keySet()).iterator();
        boolean isFirstEle = true;
        while (keys.hasNext()) {
            if (!isFirstEle) {
                appender.append(",");
            }
            String key = keys.next();
            Object val = json.get(key);
            if (val instanceof JSONObject) {
                appender.append("\"").append(key).append("\":");
                appender.append(serialJsonOrdered((JSONObject) val));
            } else if (val instanceof JSONArray) {
                JSONArray jarray = (JSONArray) val;
                appender.append("\"").append(key).append("\":[");
                boolean isFirstArrEle = true;
                for (Object o : jarray) {
                    if (!isFirstArrEle) {
                        appender.append(",");
                    }
                    if (o instanceof JSONObject) {
                        appender.append(serialJsonOrdered((JSONObject) o));
                    } else {
                        appender.append(o.toString());
                    }
                    isFirstArrEle = false;
                }
                appender.append("]");
            } else {
                String value = val.toString();
                appender.append("\"").append(key).append("\":").append("\"").append(value).append("\"");
            }
            isFirstEle = false;
        }
        appender.append("}");
        return appender.toString();
    }


}
