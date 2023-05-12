package com.gre.lxl.testCMB.atest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.testCMB.request.LcChangeBodyReq;
import com.gre.lxl.testCMB.request.Onb2dcmox1;
import com.gre.lxl.testCMB.response.LcChangeBodyResp;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_CHANGE;

/**
 * 改证经办
 * 修改信用证经办，用于信用证修改金额，条款等信息。
 *
 * @author lxl
 * @date 2022/5/30 14:01
 */
@Slf4j
public class LcChangeTest {

    public static void main(String[] args) {
//        String funCode = "ONB2DCMO";
        Onb2dcmox1 onb2dcmox1 = new Onb2dcmox1();
        onb2dcmox1.setBusmod("0000476441");
        onb2dcmox1.setPatsub("00001");
        onb2dcmox1.setExtidx("20220525SUME00004");
        onb2dcmox1.setIlcnum("153C1239ECDB2002");
        onb2dcmox1.setCtanam("苏美达1");
        onb2dcmox1.setCtatel("8899-99");
        onb2dcmox1.setOpnamt(new BigDecimal("456"));
        //BEN（由受益人承担），BAK（由银行承担）
        onb2dcmox1.setFeetyp("BEN");
        //当费用承担类型为BAK，则此选项必填
//        onb2dcmox1.setDctacc("");
        List<Onb2dcmox1> list = new ArrayList<>();
        list.add(onb2dcmox1);
        LcChangeBodyReq lcChangeBodyReq = new LcChangeBodyReq();
        lcChangeBodyReq.setOnb2dcmox1(list);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootResp rootResp = CmbClient.postToCMB(lcChangeBodyReq, LC_CHANGE);
            if (rootResp != null) {
                String resultcode = rootResp.getResponse().getHead().getResultcode();
                log.info("resultcode: {}", rootResp.getResponse().getHead().getResultcode());
                log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
                if ("SUC0000".equals(resultcode)) {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcChangeBodyResp lcChangeBodyResp = objectMapper.readValue(string, LcChangeBodyResp.class);
                    System.out.println(lcChangeBodyResp.getOnb2dcmoz1());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
