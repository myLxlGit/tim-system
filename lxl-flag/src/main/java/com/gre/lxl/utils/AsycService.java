package com.gre.lxl.utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author lxl
 * @date 2022/1/16 14:00
 */
@Service
public class AsycService {

    @Async
    public void sendMsg() {
        System.out.println("异步线程 =====> 开始 =====> " + System.currentTimeMillis());
        try{
            Thread.sleep(5000);
            System.out.println("异步线程 =====> 结束 =====> " + System.currentTimeMillis());
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }


}
