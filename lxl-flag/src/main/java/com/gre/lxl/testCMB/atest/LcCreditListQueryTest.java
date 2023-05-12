package com.gre.lxl.testCMB.atest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.testCMB.common.LcCreditListQueryBody;
import com.gre.lxl.testCMB.common.Onb2dcqey1;
import com.gre.lxl.testCMB.common.Onb2dcqez1;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_CREDIT;

/**
 * 授信列表查询 测试
 * 用于无保证金模式下，根据客户号查询授信编号及风险额度编号信息。
 * @author lxl
 * @date 2022/5/30 9:43
 */
@Slf4j
public class LcCreditListQueryTest {

    public static void main(String[] args) {
//        String funCode = "ONB2DCQE";
        Onb2dcqey1 onb2dcqey1 = new Onb2dcqey1();
        onb2dcqey1.setCltnbr("7559360487");
        //第一次传空，当返回接口有本接口并且该接口的续传字段有值，说明需要续传，取出续传字段放到下次请求报文中继续查询
        onb2dcqey1.setCtnkey("");
        onb2dcqey1.setBusmod("0000462162");
        onb2dcqey1.setPatsub("00002");
        onb2dcqey1.setBustyp("O");
        List<Onb2dcqez1> getList = new ArrayList<>();
        List<Onb2dcqez1> lists = getLcCreditList(onb2dcqey1,getList,LC_CREDIT, 1);
        log.info("调用接口授信列表查询：{}",lists);
    }

    private static List<Onb2dcqez1> getLcCreditList(Onb2dcqey1 onb2dcqey1, List<Onb2dcqez1> lists , String funCode,int step) {
        ObjectMapper objectMapper = new ObjectMapper();
        while (step > 0) {
            List<Onb2dcqey1> sendList = new ArrayList<>();
            sendList.add(onb2dcqey1);
            LcCreditListQueryBody lcCreditListQueryBody = new LcCreditListQueryBody();
            lcCreditListQueryBody.setOnb2dcqey1(sendList);
            log.info("调用招商授信列表查询，当前调用次数 = {}",step);
            RootResp rootResp = CmbClient.postToCMB(lcCreditListQueryBody, funCode);
            if (rootResp != null) {
                String resultcode = rootResp.getResponse().getHead().getResultcode();
                log.info("resultcode: {}", resultcode);
                log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
                try {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcCreditListQueryBody lcAgreementNumberQueryBodyResp = objectMapper.readValue(string, LcCreditListQueryBody.class);
                    List<Onb2dcqey1> Onb2dcqey1 = lcAgreementNumberQueryBodyResp.getOnb2dcqey1();
                    List<Onb2dcqez1> onb2dcqez1 = lcAgreementNumberQueryBodyResp.getOnb2dcqez1();
                    if (!CollectionUtils.isEmpty(onb2dcqez1)) {
                        lists.addAll(onb2dcqez1);
                    }
                    if (CollectionUtils.isEmpty(Onb2dcqey1)) {
                        log.info("续传字段，查询数据为空");
                        step = 0;
                        log.info("续传字段不存在，停止调用，此时调用次数变为 = {}",step);
                    } else {
                        Onb2dcqey1 respQey1 = Onb2dcqey1.get(0);
                        onb2dcqey1.setCtnkey(respQey1.getCtnkey());
                        step++;
                        log.info("续传字段存在，当前调用次数+1后得到 = {}",step);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (step > 0) {
                    getLcCreditList(onb2dcqey1, lists, funCode, step);
                }
            }
        }
        return lists;
    }
}
