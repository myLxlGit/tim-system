package com.gre.lxl.testDelay.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


@Data
@AllArgsConstructor
public class ReceiptDelayTask implements Delayed {

    private int startNo;

    private List<DelayUser> rows;

    private long startTime;

    /**
     * 剩余时间 = 到期时间 - 当前时间
     */
    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * 优先级规则：两个任务比较，时间短的优先执行
     */
    @Override
    public int compareTo(@NonNull Delayed o) {
        return (int)(this.startTime - ((ReceiptDelayTask) o).getStartTime());
    }
}
