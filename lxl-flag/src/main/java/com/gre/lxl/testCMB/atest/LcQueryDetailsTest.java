package com.gre.lxl.testCMB.atest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.testCMB.request.LcQueryBodyReq;
import com.gre.lxl.testCMB.request.Onb2dcqlx1;
import com.gre.lxl.testCMB.response.LcQueryBodyResp;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_QUERY_DETAILS;

/**
 * 信用证列表查询
 * 通过此接口获取提交信用证后的信用证状态，开证成功后的信用证号等信用证基本信息。
 * 入参为信用证id，个数限制为100个。
 *
 * @author lxl
 * @date 2022/5/27 9:25
 */
@Slf4j
public class LcQueryDetailsTest {

    public static void main(String[] args) {
//        String funCode = "ONB2DCQL";
        List<Onb2dcqlx1> list = new ArrayList<>();
        Onb2dcqlx1 onb2dcqlx1 = new Onb2dcqlx1();
        onb2dcqlx1.setIlcIdx("154CA548B15B8001");
//        Onb2dcqlx1 onb2dcqlx2 = new Onb2dcqlx1();
//        onb2dcqlx2.setIlcIdx("1234564");
        list.add(onb2dcqlx1);
//        list.add(onb2dcqlx2);
        LcQueryBodyReq lcQueryBodyReq = new LcQueryBodyReq();
        lcQueryBodyReq.setOnb2dcqlx1(list);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootResp rootResp = CmbClient.postToCMB(lcQueryBodyReq, LC_QUERY_DETAILS);
            if (rootResp != null) {
                String resultcode = rootResp.getResponse().getHead().getResultcode();
                log.info("resultcode: {}", resultcode);
                log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
                if ("SUC0000".equals(resultcode)) {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
//                    Class<?> aClass = Class.forName("com.gre.lxl.testCMB.response.LcQueryBodyResp");
//                    Method method = aClass.getDeclaredMethod("getOnb2dcqlz1");
//                    method.invoke(t);
                    LcQueryBodyResp lcQueryBodyResp = objectMapper.readValue(string, LcQueryBodyResp.class);
                    log.info("查询信用证信息 = {}", lcQueryBodyResp.getOnb2dcqlz1());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
