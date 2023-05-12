package com.gre.lxl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CyclicBarrier;

/**
 * @author lxl
 * @date 2022/1/27 13:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker extends Thread{
    private CyclicBarrier cyclicBarrier;

    @Override
    public void run() {
        super.run();

        try {
            System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "开始执行");
            // 工作线程开始处理，这里用Thread.sleep()来模拟业务处理
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
