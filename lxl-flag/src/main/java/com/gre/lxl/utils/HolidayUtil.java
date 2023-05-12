package com.gre.lxl.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gre.lxl.DayStatusEnum;
import com.gre.lxl.common.exception.FlowException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @date 2021/11/30 15:24
 */
public class HolidayUtil {

    public static Map<Date, DayStatusEnum> holidayByList(String year){
        String httpUrl = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query="+year+"&resource_id=6018&format=json";
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        Map<Date,DayStatusEnum> dateMap = new HashMap<Date,DayStatusEnum>();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray holidayJsonArr = jsonObject.getJSONArray("data").getJSONObject(0).getJSONArray("holiday");
            List<String> holidays = holidayJsonArr.stream().map(h -> {
                JSONObject hJsonObj = (JSONObject) h;
                JSONArray list = hJsonObj.getJSONArray("list");
                if(list.size()>0){
                    for(int i=0;i<list.size();i++){
                        JSONObject job = list.getJSONObject(i);   // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        String sts = (String)job.get("status");
                        if (sts.equals("1")) {
                            dateMap.put(DateUtils.getDateByString((String)job.get("date"), "yyyy-MM-dd"),DayStatusEnum.HOLIDAYS);
                        }else {
                            dateMap.put(DateUtils.getDateByString((String)job.get("date"), "yyyy-MM-dd"),DayStatusEnum.WORKDAY);
                        }
                    }
                }
                return "1";
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new FlowException("获取节假日出错，请联系管理员" + e);
        }
        return dateMap;
    }

    public static List<String> holidayByList1(String year) {
        String httpUrl = "https://tool.bitefu.net/jiari/?d=" + year;
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        List<String> list = null;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject holidays = (JSONObject) jsonObject.get(year);
            Iterator iter = holidays.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String md = String.valueOf(entry.getKey());
                String date = year+md;
                list.add(date);
            }
        } catch (Exception e) {
            list = Collections.emptyList();
        }
        return list;
    }
}
