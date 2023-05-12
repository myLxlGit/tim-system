package com.gre.lxl.testCMB.atest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.testCMB.request.LcChangeQueryReq;
import com.gre.lxl.testCMB.request.Onb2dcmlx1;
import com.gre.lxl.testCMB.response.LcChangeQueryBodyResp;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_CHANGE_QUERY;

/**
 * 改证列表查询
 * 修改信用证列表查询，用于查询修改信用证的业务状态，金额，经办日期等基本信息。
 * 入参为信用证id，限制大小为100个。
 *
 * @author lxl
 * @date 2022/5/30 14:19
 */
@Slf4j
public class LcChangeQueryTest {

    public static void main(String[] args) {
//        String funCode = "ONB2DCML";
        Onb2dcmlx1 onb2dcmlx1 = new Onb2dcmlx1();
        onb2dcmlx1.setIlcidx("153C1239ECDB2002");

        List<Onb2dcmlx1> list = new ArrayList<>();
        list.add(onb2dcmlx1);

        LcChangeQueryReq lcChangeQueryReq = new LcChangeQueryReq();
        lcChangeQueryReq.setOnb2dcmlx1(list);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootResp rootResp = CmbClient.postToCMB(lcChangeQueryReq, LC_CHANGE_QUERY);
            if (rootResp != null) {
                String resultcode = rootResp.getResponse().getHead().getResultcode();
                log.info("resultcode: {}", rootResp.getResponse().getHead().getResultcode());
                log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
                if ("SUC0000".equals(resultcode)) {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcChangeQueryBodyResp lcChangeQueryBodyResp = objectMapper.readValue(string, LcChangeQueryBodyResp.class);
                    System.out.println(lcChangeQueryBodyResp.getOnb2dcmlz1());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
