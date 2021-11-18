package com.gre.lxl.service;


import com.gre.lxl.domain.ReceiverUser;
import com.gre.lxl.domain.dto.ProcessDTO;

/**
 * @author lxl
 * @date 2021/10/11 14:32
 */
public interface IFlowService {

    int startConFlow(ReceiverUser receiverUser);

    int completeConFlow(ProcessDTO processDTO);

}
