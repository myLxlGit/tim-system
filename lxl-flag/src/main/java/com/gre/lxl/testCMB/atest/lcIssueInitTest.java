package com.gre.lxl.testCMB.atest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.testCMB.request.LcIssueInitBodyReq;
import com.gre.lxl.testCMB.request.Onbdcconx1;
import com.gre.lxl.testCMB.request.Onbdcgodx1;
import com.gre.lxl.testCMB.request.Onbdcmstx1;
import com.gre.lxl.testCMB.response.LcIssueInitBodyResp;
import com.gre.lxl.testCMB.response.Onb2dcopz1;
import com.gre.lxl.testCMB.response.RootResp;
import com.gre.lxl.testCMB.serive.impl.CmbClient;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_ISSUE_INIT;

/**
 * 信用证经办
 * 用于客户填写信用证要素信息，进行提交经办。
 *
 * @author lxl
 * @date 2022/5/27 9:03
 */
@Slf4j
public class lcIssueInitTest {

    public static void main(String[] args) {
        //测试信用证经办
//        String funCode = "ONB2DCOP";
        List<Onbdcmstx1> tx1s = new ArrayList<>();
        Onbdcmstx1 tx1 = new Onbdcmstx1();
        tx1.setOprtyp("C");
        //业务模式
        tx1.setBusmod("0000462161");
        //业务子模式
        tx1.setPatsub("00001");

        tx1.setExtidx("20220530SUME00003");
        //客户号 银行提供
        tx1.setCltnbr("7559360487");
        //扣费账号  根据申请的招商来的结算户
        tx1.setDctacc("755936048710801");
        //1.
//        tx1.setLibrat("1");

        tx1.setCtanam("苏美达");
        tx1.setCtatel("18344445567");
        tx1.setTrsbrn("123456");
        //2.
//        tx1.setAppdes("");

        //使用全额保证金模式(FULL)的客户需线下与网点签署《国际信用证开证合作协议》，
        //提供协议编号查询，以供经办接口使用。
        //FULL,PART,NONE
        tx1.setDetmod("NONE");
        //3.协议编号
//        tx1.setTexnbr("XYBH001");

        //无保证金模式下，根据客户号查询授信编号及风险额度编号信息。
        //4 授信编号
        tx1.setCdtnbr("100XY2021001893");
        //5 额度编号
        tx1.setRsknbr("EL2103300000194");

        tx1.setTrscod("121010"); //交易编码
        tx1.setFomtrs("N");
        tx1.setCfmins("Y");
        tx1.setCfmprt("BEN");
        tx1.setCfmrim("Y");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR,+1);
        tx1.setExpdat(calendar.getTime());
        tx1.setPlaprt("sss");
        tx1.setAppadd("12");
        tx1.setBennam("12");
        tx1.setBenadd("adddd");
        tx1.setBencnr("CN");
        //6.
//        tx1.setBenacc("");
        //7.
//        tx1.setAdvbnk("");
        //8.
//        tx1.setAdvadd("");
        //9.
//        tx1.setAdvbk2("");
        //10.
//        tx1.setAdvad2("");

        tx1.setOpncur("10"); //Opncur  CNY
        tx1.setOpnamt(new BigDecimal("158"));
        //11.
//        tx1.setIcspct("");
        //12.
//        tx1.setDcspct("");

        tx1.setSpebnk("xxx");
        tx1.setPaytyp("NG");
        tx1.setPaydrt("ST");
        //13.
//        tx1.setDrtday("");
        //14.
//        tx1.setDrttyp("");

        tx1.setDraact("N");
        //15.
        tx1.setDrabnk(""); //受票行
        //16.
        tx1.setDrapct(""); //汇票占比 填写错误
        //17.
        tx1.setDraamt("123"); //汇票金额 填写错误

        tx1.setPtlshp("A");
        //18.
//        tx1.setPtldtl("");

        tx1.setTrstem("A");
        //20.
//        tx1.setTrsdtl("");
        //21.
//        tx1.setFrmadd("");
        //22.
//        tx1.setLodpot("");
        //23.
//        tx1.setDespot("");
        //24.
//        tx1.setFinadd("");
        //25.
//        tx1.setShpdat("");
        //26.
//        tx1.setShpped("");

        tx1.setReptyp("PWV");
        //27.
//        tx1.setPrsday("");
        //28.
//        tx1.setPrsunt("");

        tx1.setFeetem("fee");
        tx1s.add(tx1);

        List<Onbdcgodx1> dx1s = new ArrayList<>();
        Onbdcgodx1 dx1 = new Onbdcgodx1();
        dx1.setTemtyp("G");
        dx1.setTemtxt("123");
        Onbdcgodx1 dx2 = new Onbdcgodx1();
        dx2.setTemtyp("D");
        dx2.setTemtxt("123");
        dx1s.add(dx1);
        dx1s.add(dx2);

        List<Onbdcconx1> nx1s = new ArrayList<>();
        Onbdcconx1 nx1 = new Onbdcconx1();
        nx1.setConnbr("2016222050");
        nx1.setConccy("10");
        nx1.setConamt(new BigDecimal("50"));
        nx1s.add(nx1);

        LcIssueInitBodyReq lcIssueInitBodyReq = new LcIssueInitBodyReq();
        lcIssueInitBodyReq.setOnbdcmstx1(tx1s);
        lcIssueInitBodyReq.setOnbdcgodx1(dx1s);
        lcIssueInitBodyReq.setOnbdcconx1(nx1s);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RootResp rootResp = CmbClient.postToCMB(lcIssueInitBodyReq, LC_ISSUE_INIT);
            if (rootResp != null) {
                String resultcode = rootResp.getResponse().getHead().getResultcode();
                log.info("resultcode: {}", rootResp.getResponse().getHead().getResultcode());
                log.info("resultmsg: {}", rootResp.getResponse().getHead().getResultmsg());
                if ("SUC0000".equals(resultcode)) {
                    String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                    LcIssueInitBodyResp lcIssueInitBodyResp = objectMapper.readValue(string, LcIssueInitBodyResp.class);
                    Onb2dcopz1 onb2dcopz1 = lcIssueInitBodyResp.getOnb2dcopz1().get(0);
                    log.info("经办成功输出审批流编号和信用证id");
                    log.info("审批流编号 = {}",onb2dcopz1.getAppnbr());
                    log.info("信用证ID = {}",onb2dcopz1.getIlcidx());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //1.Extidx 20220525SUME00002 成功 这一笔交易成功
//        签名原文: {"request":{"body":{"onbdcconx1":[{"conamt":50,"conccy":"10","connbr":"2016222050"}],"onbdcgodx1":[{"temtxt":"123","temtyp":"G"},{"temtxt":"123","temtyp":"D"}],"onbdcmstx1":[{"appadd":"12","benadd":"adddd","bencnr":"CN","bennam":"12","busmod":"0000462162","cdtnbr":"100XY2021001893","cfmins":"Y","cfmprt":"BEN","cfmrim":"Y","cltnbr":"7559360487","ctanam":"苏美达","ctatel":"18344445567","dctacc":"755936048710801","detmod":"NONE","draact":"N","draamt":"123","drabnk":"","drapct":"","expdat":"20230530","extidx":"20220525SUME00002","feetem":"fee","fomtrs":"N","opnamt":"123","opncur":"10","oprtyp":"C","patsub":"00002","paydrt":"ST","paytyp":"NG","plaprt":"sss","ptlshp":"A","reptyp":"PWV","rsknbr":"EL2103300000194","spebnk":"xxx","trsbrn":"123456","trscod":"121010","trstem":"A"}]},"head":{"funcode":"ONB2DCOP","reqid":"202205271725340000001","userid":"N002987421"}},"signature":{"sigdat":"__signature_sigdat__","sigtim":"20220527172534"}}
//        签名结果: UnYk5cD7F5ot2V+bBJYVDcQSgLrt1UFwXEzPK7SRI7B4LWGr+fsFxxygmFlmzlSnOeJyNCYNpFj3WZeERny9oQ==
//        加密前req: {"request":{"body":{"onbdcconx1":[{"conamt":50,"conccy":"10","connbr":"2016222050"}],"onbdcgodx1":[{"temtxt":"123","temtyp":"G"},{"temtxt":"123","temtyp":"D"}],"onbdcmstx1":[{"appadd":"12","benadd":"adddd","bencnr":"CN","bennam":"12","busmod":"0000462162","cdtnbr":"100XY2021001893","cfmins":"Y","cfmprt":"BEN","cfmrim":"Y","cltnbr":"7559360487","ctanam":"苏美达","ctatel":"18344445567","dctacc":"755936048710801","detmod":"NONE","draact":"N","draamt":"123","drabnk":"","drapct":"","expdat":"20230530","extidx":"20220525SUME00002","feetem":"fee","fomtrs":"N","opnamt":"123","opncur":"10","oprtyp":"C","patsub":"00002","paydrt":"ST","paytyp":"NG","plaprt":"sss","ptlshp":"A","reptyp":"PWV","rsknbr":"EL2103300000194","spebnk":"xxx","trsbrn":"123456","trscod":"121010","trstem":"A"}]},"head":{"funcode":"ONB2DCOP","reqid":"202205271725340000001","userid":"N002987421"}},"signature":{"sigdat":"UnYk5cD7F5ot2V+bBJYVDcQSgLrt1UFwXEzPK7SRI7B4LWGr+fsFxxygmFlmzlSnOeJyNCYNpFj3WZeERny9oQ==","sigtim":"20220527172534"}}
//        加密后req: zkgRvQ/eKreqapNdYdwpa5zy+KwN96XIe96uXJpp1cKAFFSd0TkFeoY4ASoSgkxlIh7gK6ZnHGQEvenXsXUPyTXHyvbTYrHrjsVtvar5NdAne1C9hTMZiECXO2U4CjNU4ru11Wb51T78l893sXtqyPcpisLd95O/FVPBHjwn5YR8aIEifJKU9vnuiTlNePvFdsxowCqGqJbmfMc77/11gHQSiwr5Qeu/Ua0SygPWsLJ42Dr6pg9RnvluK+Lo//AtOdds/dhEUP10c3PsqalvYR4E0FaTpDSYu1SempOwa+oLwybWkTaOA23P9NVFTEpHGncEhMbLWEWjdI6feO5m/xeTUvIg9D3JenqMebBh0iN8I+mp/B9kD0gq+wqI4nc1In6CBfDSaULd6jouYA0dnCIrGQOo+xLNJO7762wbBe4fy8MUyupERty5nZA6fKSBUmVzugaFnSQEU/ORBmc0fyd/HFyedfiZFe8xz3/eVz4nlfZc7sq9MVkqHu5gjQa917GKCXl0hCFGGZOf4hoIOBLC9YkikELdTIklZXcl2v382DkMCrStVOzXAZFsxprkhXw7fzNsD+jE9CxdL6Jxu1ZQsKWVjXau9EsOERI2cTGH4owbfltOP6Yj7KcxfzEdtygvL1ajdEOyj58d+mK4hiHq0CA7SAK1mNMoNuXhqEjX18mPBAmcb58aHG/JMX1yNzs1U33tzMLZ9v99YEJm6Ewxbv8Z1+vHbKqYWPMWQuXkGYrdCypjrBYbcqy1517YTZ3LZt+0T6tlbIWImVSJJ8Ozos4kcgvSQNz6GDhCGeW6lCsSVs9T2gH9hECUxi7esqJd8pJa0Q0/LRw5uxcVA385cuUOclKfbSID6Hb8+lTMDhEiv1CBrqLOuw8fRqEYVx3cLhDN+R+MRA6gIJylaJcxk885MjrSH0uz16c/WLcjYwh98CR+xaOMHLvkyeISLcWyfRh2841ohfK464crMYpEWDygil/ob3HmwxRl+1+r3/KFzn41Uei4faJQbYPpdfMVxPQyuDrSjoZhhd03ufPn1Atq0yD+w1JmxyISef3E2FoPqjeQ9TfaSOhb0S7teaRMpYBJFJcfQn271NFsRemY5kyYcQ9AnBfwbX44zyc9L27dlV//MIp4d2YrCMmI1/lTgQDLClogrOtYRzvG4Vsr9r475RFMbfPLniFT+u5vf5H0QrhpHMsbO4j4g6ByyO2Ctgzw+ECHZIyR1HCi4L13VbqKIbCn/tHjGvWCh6wvpWYOpBw1TQ0ohzU4EI1PqzZzhbqcEm8pqnGG6lXLzCqQh4/lzwrfMrAUBo8Rzt6gGqheDR6Di5vd7q2yr9rAeL6XQdn6Ee4Mxsk4wzqUkA==
//        res响应: itPO/IFPdnbEgG+jrhOKHmeMGiU3Wid7lilBwl/QPU0H9+3Ml/VPcm81YFUHd3E7M3CpOHHu6IyGVH0Ex+MXBg41uuxqt7u87JwCOxErp9KSfjhZzEI2v77vo/nyT48K1vAPgEEijTawp0mu/zKxYmINc8lrjnK+9N6EULrLDdGqeVRF9KfZPPpPUkYnPA7IIGE2czUir85uTvy9HS+TlcAP6RAxSyA8He93K1MZ/6LHwlBj+zf6xZfGmt6aBKMQPtCXyi1EckF6+YhIZQlTXs7IxHfUfXGSmEMip6GlZXBfqcr/trvt3/xsQfVEaRjG1gvdn9MituFNa1OKj7jNJnt9vSsuj53IvR5WQyPnMDKABeP09d6WGgpDqeVJE1OzT0ZHElcuU8eRhYSsVV3HobDvGCoxANfjWh3fi6che5Y5bCNRNI67WOxpev/dlPygVHLpr/MBvYjGBk3x5hYERkTevyM1SbPgBZQvrsejGjBwy3dJpOJKtQ1wYkGOnQLzVdN+z9/yhr0yMxSGH8C0FelcfmiPVmRNd5ktMsTg/N8QGRSQ9NerYSCn3y770fhF
//        res decrypt解密: {"response":{"body":{"onb2dcopz1":[{"appnbr":"BAK10DX0EN1725370001","ilcidx":"153C1239ECDB2002"}]},"head":{"bizcode":"","funcode":"ONB2DCOP","reqid":"202205271725340000001","resultcode":"SUC0000","resultmsg":"","rspid":"202205271725364310001CDCServer-tom","userid":"N002987421"}},"signature":{"sigdat":"Jh2jPgDSvIEnKtZnciC1DZTQ7DnVgZ+QnLD5dxr2VPrV7pPl9fwdnjDzhr2VK8U17kmgxJ3MTWEF8IJ7PvVLZw==","sigtim":"20220527172539"}}
//        验签原文: {"response":{"body":{"onb2dcopz1":[{"appnbr":"BAK10DX0EN1725370001","ilcidx":"153C1239ECDB2002"}]},"head":{"bizcode":"","funcode":"ONB2DCOP","reqid":"202205271725340000001","resultcode":"SUC0000","resultmsg":"","rspid":"202205271725364310001CDCServer-tom","userid":"N002987421"}},"signature":{"sigdat":"__signature_sigdat__","sigtim":"20220527172539"}}
//        验签签名值: Jh2jPgDSvIEnKtZnciC1DZTQ7DnVgZ+QnLD5dxr2VPrV7pPl9fwdnjDzhr2VK8U17kmgxJ3MTWEF8IJ7PvVLZw==
//        验签结果: true
        // appnbr BAK10DX0EN1725370001   审批流编号
        // ilcidx 153C1239ECDB2002   信用证ID

        //2.Extidx 20220530SUME00001 成功
        //审批流编号 = BAK102T0EQ1458390001
        //信用证ID = 153FCD59D85B2001

        //3.Extidx 20220530SUME00003 成功
        //审批流编号 = BAK10DX0GX1423490001
        //信用证ID = 154CA548B15B8001
    }

}
