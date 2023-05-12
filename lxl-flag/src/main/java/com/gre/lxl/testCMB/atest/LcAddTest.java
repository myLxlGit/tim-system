package com.gre.lxl.testCMB.atest;

import com.gre.lxl.testCMB.request.LcAddBodyReq;
import com.gre.lxl.testCMB.request.Onb2dcadx1;
import com.gre.lxl.testCMB.request.Onbdcecsx1;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_ADD;

/**
 * 补档
 * 信用证申请提交到银行后，银行审查相关材料，
 * 审核不通过，会给客户发起补档请求，要求客户补充相关资料。可使用此接口上传相关文件资料。
 *
 * @author lxl
 * @date 2022/5/29 20:01
 */
@Slf4j
public class LcAddTest {

    public static void main(String[] args) {
//        String funCode = "ONB2DCAD";
        Onb2dcadx1 onb2dcadx1 = new Onb2dcadx1();
        List<Onb2dcadx1> onb2dcadx1s = new ArrayList<>();
        onb2dcadx1.setTskidx("89");
        onb2dcadx1.setTskflg("S");
        onb2dcadx1s.add(onb2dcadx1);

        //传输文件 todo
        Onbdcecsx1 onbdcecsx1 = new Onbdcecsx1();

        LcAddBodyReq lcAddBodyReq = new LcAddBodyReq();
        lcAddBodyReq.setOnb2dcadx1(onb2dcadx1s);
        try {
            RootResp rootResp = CmbClient.postToCMB(lcAddBodyReq, LC_ADD);
            if (rootResp != null) {
                String resultcode = rootResp.getResponse().getHead().getResultcode();
                //若正常，则返回为空
                log.info("resultcode: {}", resultcode);
                log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
