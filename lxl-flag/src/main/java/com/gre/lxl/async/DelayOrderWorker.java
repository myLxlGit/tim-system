package com.gre.lxl.async;

import lombok.Data;

/**
 * @author lxl
 * @date 2022/2/28 18:42
 */
@Data
public class DelayOrderWorker implements Runnable {

    private String str;

    @Override
    public void run() {
        //相关业务逻辑处理
        System.out.println(Thread.currentThread().getName()+" do something ……" + str);
    }

    public DelayOrderWorker(String str) {
        this.str = str;
    }

    public DelayOrderWorker() {
    }
}
