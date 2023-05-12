package com.gre.lxl.testCMB.atest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.testCMB.request.LcBusModQueryBodyReq;
import com.gre.lxl.testCMB.request.Onb2dcmdx1;
import com.gre.lxl.testCMB.response.LcBusModQueryBodyResp;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_BUS_MOD;

/**
 * 模式列表查询 根据业务代码，查询当前用户可经办的所有业务模式。
 *
 * @author lxl
 * @date 2022/5/30 11:50
 */
@Slf4j
public class LcBusModQueryTest {

    public static void main(String[] args) {
//        String funCode = "ONB2DCMD";
        Onb2dcmdx1 onb2dcmdx1 = new Onb2dcmdx1();
        //国际信用证
        onb2dcmdx1.setBuscod("N05020");
        List<Onb2dcmdx1> list = new ArrayList<>();
        list.add(onb2dcmdx1);

        LcBusModQueryBodyReq lcBusModQueryBodyReq = new LcBusModQueryBodyReq();
        lcBusModQueryBodyReq.setOnb2dcmdx1(list);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootResp rootResp = CmbClient.postToCMB(lcBusModQueryBodyReq, LC_BUS_MOD);
            if (rootResp != null) {
                String resultcode = rootResp.getResponse().getHead().getResultcode();
                log.info("resultcode: {}", resultcode);
                log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
                if ("SUC0000".equals(resultcode)) {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcBusModQueryBodyResp lcBusModQueryBodyResp = objectMapper.readValue(string, LcBusModQueryBodyResp.class);
                    log.info("模式列表查询数据 = {}",lcBusModQueryBodyResp.getOnb2dcmdz1());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //包含业务模式编号、业务模式子编号，
        //可用于填写信用证经办时的这两项要素信息。
        //如零审批标记为Y，
        //则代表不需要在网银客户端进行下一级审批，直接提交至银行。
        //用户N002987421 N05020(开证)
        //[Onb2dcmdz1(busmod=0000462162, patsub=00002, modnam=信用证无审批, zerflg=Y),
        // Onb2dcmdz1(busmod=0000462161, patsub=00001, modnam=信用证有审批, zerflg=N)]

        //用户N002987421 N05030(改证)
        //[Onb2dcmdz1(busmod=0000476441, patsub=00001, modnam=信用证修改无审批, zerflg=Y),
        //Onb2dcmdz1(busmod=0000476442, patsub=00002, modnam=信用证修改有审批, zerflg=N)]
    }
}
