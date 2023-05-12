package com.gre.lxl.testCMB.serive;

import com.gre.lxl.testCMB.request.LcIssueInitBodyReq;
import com.gre.lxl.testCMB.request.Onb2dcmdx1;
import com.gre.lxl.testCMB.request.Onb2dcqlx1;
import com.gre.lxl.testCMB.request.Onb2dcqtx1;
import com.gre.lxl.testCMB.response.Onb2dcmdz1;
import com.gre.lxl.testCMB.response.Onb2dcqlz1;
import com.gre.lxl.testCMB.response.Onb2dcqtz1;
import com.gre.lxl.testCMB.response.RootResp;

import java.util.List;

/**
 * 招商银行接口
 *
 * @author lxl
 * @date 2022/5/27 14:00
 */
public interface ICmbClientService {

    /**
     * 根据信用证id查询信用证信息
     * 通过此接口获取提交信用证后的信用证状态，开证成功后的信用证号等信用证基本信息。
     *
     * @param list 信用证id
     * @return list
     */
    List<Onb2dcqlz1> queryLcDetails(List<Onb2dcqlx1> list);

    /**
     * 信用证经办
     *
     * @param lcIssueInitBodyReq 经办body
     * @return bean
     */
    RootResp LcIssueInit(LcIssueInitBodyReq lcIssueInitBodyReq);

    /**
     * 查询模式列表
     * 根据业务代码，查询当前用户可经办的所有业务模式。
     *
     * @param list 集合
     * @return list
     */
    List<Onb2dcmdz1> queryLcBusMode(List<Onb2dcmdx1> list);

    /**
     * 查询协议编号列表
     * 使用全额保证金模式(FULL)的客户需线下与网点签署《国际信用证开证合作协议》，此接口提供协议编号查询，以供经办接口使用。
     *
     * @param list 集合
     * @return list
     */
    List<Onb2dcqtz1> queryLcAgreementNumber(List<Onb2dcqtx1> list);
}
