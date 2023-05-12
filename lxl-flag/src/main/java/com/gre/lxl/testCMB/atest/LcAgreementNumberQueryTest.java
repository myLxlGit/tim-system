package com.gre.lxl.testCMB.atest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.testCMB.request.LcAgreementNumberQueryBodyReq;
import com.gre.lxl.testCMB.request.Onb2dcqtx1;
import com.gre.lxl.testCMB.response.LcAgreementNumberQueryBodyResp;
import com.gre.lxl.testCMB.response.Onb2dcqtz1;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_AGREEMENT_NUMBER;

/**
 * 协议编号列表查询
 * 使用全额保证金模式(FULL)的客户需线下与网点签署《国际信用证开证合作协议》，
 * 此接口提供协议编号查询，以供经办接口使用。
 *
 * @author lxl
 * @date 2022/5/29 21:17
 */
@Slf4j
public class LcAgreementNumberQueryTest {

    public static void main(String[] args) {
//        String funCode = "ONB2DCQT";
        Onb2dcqtx1 onb2dcqtx1 = new Onb2dcqtx1();
        //深圳分行 业务机构根据账号开户行来的
        onb2dcqtx1.setBrnnbr("755912"); //
        onb2dcqtx1.setCltnbr("7559360487");
        onb2dcqtx1.setBusmod("0000462161");
        onb2dcqtx1.setPatsub("00001");
        onb2dcqtx1.setBustyp("O");
        List<Onb2dcqtx1> list = new ArrayList<>();
        list.add(onb2dcqtx1);
        LcAgreementNumberQueryBodyReq lcAgreementNumberQueryBodyReq = new LcAgreementNumberQueryBodyReq();
        lcAgreementNumberQueryBodyReq.setOnb2dcqtx1(list);

        ObjectMapper objectMapper = new ObjectMapper();
        RootResp rootResp = CmbClient.postToCMB(lcAgreementNumberQueryBodyReq, LC_AGREEMENT_NUMBER);
        if (rootResp != null) {
            String resultcode = rootResp.getResponse().getHead().getResultcode();
            log.info("resultcode: {}", resultcode);
            log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
            if ("SUC0000".equals(resultcode)) {
                try {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcAgreementNumberQueryBodyResp lcAgreementNumberQueryBodyResp = objectMapper.readValue(string, LcAgreementNumberQueryBodyResp.class);
                    List<Onb2dcqtz1> onb2dcqtz1 = lcAgreementNumberQueryBodyResp.getOnb2dcqtz1();
                    if (CollectionUtils.isEmpty(onb2dcqtz1)) {
                        log.info("查询数据为空");
                    } else {
                        log.info("协议编号列表数据 = {}", onb2dcqtz1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }


    }
}
