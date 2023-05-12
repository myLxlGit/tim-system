package com.gre.lxl.Aliyun.atest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gre.lxl.Aliyun.atest.domain.WuLog;
import com.gre.lxl.Aliyun.atest.service.AliTestService;
import com.gre.lxl.async.DelayOrderQueueManager;
import com.gre.lxl.async.DelayOrderWorker;
import com.gre.lxl.client.WebSocketServer;
import com.gre.lxl.common.core.domain.model.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 阿里云市场购买的接口测试
 *
 * @author lxl
 * @date 2021/11/24 10:06
 */
@RestController
@RequestMapping("/ali/mpi")
public class AliTestController {

    @Autowired
    private AliTestService testService;
    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 全国物流查询接口测试
     *
     * no 订单编号:手机号后四位
     * type 物流公司简称
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/testTask")
    public AjaxResult testTask() throws Exception {
        String name = "ajaxchattest";
        webSocketServer.sendToName(name,"你好");


//        DelayOrderWorker delayOrderWorker = new DelayOrderWorker("我是三");
//        DelayOrderWorker delayOrderWorker1 = new DelayOrderWorker("我是六");
//        DelayOrderWorker delayOrderWorker2 = new DelayOrderWorker("我是九");
//        DelayOrderWorker delayOrderWorker3 = new DelayOrderWorker("我是60");
//        // 延迟队列管理类，将任务转化消息体并将消息体放入延迟对列中等待执行
//        DelayOrderQueueManager manager = DelayOrderQueueManager.getInstance();
//        manager.put(delayOrderWorker, 6000, TimeUnit.MILLISECONDS);
//        manager.put(delayOrderWorker3, 60000, TimeUnit.MILLISECONDS);
//        manager.put(delayOrderWorker1, 6000, TimeUnit.MILLISECONDS);
//        manager.put(delayOrderWorker2, 6000, TimeUnit.MILLISECONDS);

//        String no = "552062378137421:2036";
//        //返回map
//        Map<String,Object> mapR = testService.queryTest(no,"HTKY");
//        //返回对象
//        WuLog htky = testService.queryTest2(no, "HTKY");


//        String host = "https://wuliu.market.alicloudapi.com";// 【1】请求地址 支持http 和 https 及 WEBSOCKET
//        String path = "/kdi";  // 【2】后缀
//        String appcode = "8f6bd0baea2644cd8d86136f4b1e6ff6"; // 【3】开通服务后 买家中心-查看AppCode
//        String no = "552062378137421:2036";// 【4】请求参数，详见文档描述
//        String type = "HTKY"; //  【4】请求参数，不知道可不填 95%能自动识别
//        String urlSend = host + path + "?no=" + no +"&type="+type;  // 【5】拼接请求链接
//        Map<String,Object> mapR = new HashMap<>();
//        try {
//            URL url = new URL(urlSend);
//            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
//            httpURLCon .setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE (中间是英文空格)
//            int httpCode = httpURLCon.getResponseCode();
//            if (httpCode == 200) {
//                String json = read(httpURLCon.getInputStream());
//                JSONObject jsonObject = JSONObject.parseObject(json);
//                Integer code = jsonObject.getInteger("status");
//                if (code == 0) {
//                    String string = JSON.toJSONString(jsonObject.get("result"));
//                    mapR = JSONObject.parseObject(string, Map.class);
//                }
//                System.out.println("正常请求计费(其他均不计费)");
//                System.out.println("获取返回的json:");
//                System.out.print(json);
//            } else {
//                Map<String, List<String>> map = httpURLCon.getHeaderFields();
//                String error = map.get("X-Ca-Error-Message").get(0);
//                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
//                    System.out.println("AppCode错误 ");
//                } else if (httpCode == 400 && error.equals("Invalid Url")) {
//                    System.out.println("请求的 Method、Path 或者环境错误");
//                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
//                    System.out.println("参数错误");
//                } else if (httpCode == 403 && error.equals("Unauthorized")) {
//                    System.out.println("服务未被授权（或URL和Path不正确）");
//                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
//                    System.out.println("套餐包次数用完 ");
//                } else {
//                    System.out.println("参数名错误 或 其他错误");
//                    System.out.println(error);
//                }
//            }
//        } catch (MalformedURLException e) {
//            System.out.println("URL格式错误");
//        } catch (UnknownHostException e) {
//            System.out.println("URL地址错误");
//        } catch (Exception e) {
//            // 打开注释查看详细报错异常信息
//            // e.printStackTrace();
//        }


//        return AjaxResult.success("操作成功",mapR);
//        return AjaxResult.success("操作成功",htky);
        return AjaxResult.success("操作成功");
    }

    /*
     * 读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
