package com.gre.lxl.testCMB.atest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.testCMB.request.LcAddQueryBodyReq;
import com.gre.lxl.testCMB.request.Onb2dctkx1;
import com.gre.lxl.testCMB.response.LcAddQueryResp;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_ADD_QUERY;

/**
 * 通过待补档列表接口，查询当前用户所涉及的补档任务，返回客户已补档，待补档，补档拒绝，银行撤销，等状态的补档记录。
 *
 * @author lxl
 * @date 2022/5/29 20:25
 */
@Slf4j
public class LcAddQueryTest {

    public static void main(String[] args) {
//        String funCode = "ONB2DCTK";
        List<Onb2dctkx1> list = new ArrayList<>();
        Onb2dctkx1 onb2dctkx1 = new Onb2dctkx1();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-5);
        onb2dctkx1.setStrdat(calendar.getTime());
        onb2dctkx1.setEnddat(new Date());
        //可以为空查全部 S(完成),W(待补档),R(客户拒绝),C(银行撤销)
        onb2dctkx1.setBussts("S");
        //单记录查询
        list.add(onb2dctkx1);
        LcAddQueryBodyReq lcAddQueryBodyReq = new LcAddQueryBodyReq();
        lcAddQueryBodyReq.setOnb2dctkx1(list);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootResp rootResp = CmbClient.postToCMB(lcAddQueryBodyReq, LC_ADD_QUERY);
            if (rootResp != null) {
                String resultcode = rootResp.getResponse().getHead().getResultcode();
                log.info("resultcode: {}", resultcode);
                log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
                if ("SUC0000".equals(resultcode)) {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcAddQueryResp lcAddQueryResp = objectMapper.readValue(string, LcAddQueryResp.class);
                    log.info("待补档列表数据 = {}",lcAddQueryResp.getOnb2dctkz1());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
