package com.gre.lxl.common.util.ip;

import com.alibaba.fastjson.JSONObject;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * 获取地址类
 *
 * @author sumec
 */
public class AddressUtils {
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    /**
     * IP地址查询地址查询接口
     */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip={1}&json=true";

    /**
     * 未知地址
     */
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
//        if (SumecConfig.isAddressEnabled()) {
            try {
                RestTemplate rest = SpringUtils.getBean(RestTemplate.class);
                ResponseEntity<JSONObject> responseEntity = rest.getForEntity(IP_URL, JSONObject.class, ip);
                if (responseEntity.getStatusCode() != HttpStatus.OK || StringUtils.isEmpty(responseEntity.getBody())) {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                String region = responseEntity.getBody().getString("pro");
                String city = responseEntity.getBody().getString("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
//        }
        return UNKNOWN;
    }
}
