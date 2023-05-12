package com.gre.lxl.Aliyun.atest.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gre.lxl.Aliyun.Client;
import com.gre.lxl.Aliyun.MpiConstant;
import com.gre.lxl.Aliyun.Request;
import com.gre.lxl.Aliyun.Response;
import com.gre.lxl.Aliyun.atest.domain.WuLog;
import com.gre.lxl.Aliyun.atest.service.AliTestService;
import com.gre.lxl.Aliyun.constant.Constants;
import com.gre.lxl.Aliyun.constant.HttpHeader;
import com.gre.lxl.Aliyun.constant.HttpSchema;
import com.gre.lxl.Aliyun.enmus.Method;
import com.gre.lxl.Aliyun.utils.JSONUtils;
import com.gre.lxl.common.exception.FlowException;
import com.gre.lxl.common.util.uuid.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lxl
 * @date 2021/11/24 10:03
 */
@Service
@Slf4j
public class AliTestServiceImpl implements AliTestService {

    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<>();




    @Override
    public Map<String, Object> queryTest(String no, String type) {
        Map<String,Object> map = new HashMap<>();
        JSONObject message = getMessage(no, type);
        if (JSONUtils.isNotEmpty(message)) {
            map = JSONObject.parseObject(JSON.toJSONString(message), Map.class);
        }
        return map;
    }

    @Override
    public WuLog queryTest2(String no, String type) {
        WuLog wuLog = new WuLog();
        JSONObject message = getMessage(no, type);
        if (JSONUtils.isNotEmpty(message)) {
            wuLog = JSONObject.parseObject(JSON.toJSONString(message), WuLog.class);
        }
        return wuLog;
    }

    private JSONObject getMessage(String no, String type) {
        Map<String, String> headers = new HashMap<>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
        headers.put("x-ca-key", MpiConstant.APP_KEY);
        headers.put("x-ca-nonce", IdUtils.randomUUID());
        headers.put("x-ca-signature-method", "HmacSHA256");

        //构建签名的字段数据
        CUSTOM_HEADERS_TO_SIGN_PREFIX.clear();
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("x-ca-key");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("x-ca-signature-method");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("X-Ca-Signature");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("x-ca-signature-headers");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("x-ca-nonce");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("x-ca-timestamp");

        Request request = new Request(Method.GET, HttpSchema.HTTPS + MpiConstant.HOST, MpiConstant.PATH, MpiConstant.APP_KEY, MpiConstant.SECRET_KEY, Constants.DEFAULT_TIMEOUT);
        request.setHeaders(headers);
        request.setSignHeaderPrefixList(CUSTOM_HEADERS_TO_SIGN_PREFIX);
        request.setTimeout(0);

        //请求的query
        Map<String, String> querys = new HashMap<>();
        querys.put("no", no);
        querys.put("type", type);
        request.setQuerys(querys);

        //调用服务端
        try {
            Response response = Client.execute(request);
            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                String body = response.getBody();
                JSONObject jsonObject = JSONObject.parseObject(body);
                Map<String,Object> map = new HashMap<>();
                Integer code = jsonObject.getInteger("status");
                //0 表示成功
                if (code == 0) {
                    return jsonObject.getJSONObject("result");
                }
            } else {
                log.error("数据错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
