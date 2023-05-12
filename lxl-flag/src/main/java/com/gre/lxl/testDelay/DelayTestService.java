package com.gre.lxl.testDelay;

import com.gre.lxl.testDelay.domain.DelayUser;
import com.gre.lxl.testDelay.domain.ReceiptDelayTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @author lxl
 * @date 2022/6/16 16:27
 */
@Service
@Slf4j
public class DelayTestService {


    private final DelayQueue<ReceiptDelayTask> delayQueue = new DelayQueue<>();

    public void test() {
        LocalDate endDate = LocalDate.now().minusDays(-1);
        LocalDate startDate = LocalDate.now().minusDays(-1);
        processDelayTask(startDate, endDate);
    }

    public void processDelayTask(LocalDate startDate, LocalDate endDate) {
        long currentTime = System.currentTimeMillis();
        List<DelayUser> ll = getTestRow(startDate,endDate,0);
        for (int i = 0; i < 3; i++) {
            List<DelayUser> rows = null;
            if (i == 0) {
                rows = ll;
            }
            delayQueue.put(new ReceiptDelayTask(i, rows, currentTime + 4000 + i));
        }
        //开启
        setUpConsumer(startDate, endDate);

    }

    private void setUpConsumer(LocalDate startDate, LocalDate endDate) {
        while (!delayQueue.isEmpty()) {
            try {
                ReceiptDelayTask take = delayQueue.take();
                List<DelayUser> rows = take.getRows();
                if (CollectionUtils.isEmpty(rows)) {
                    rows = getTestRow(startDate,endDate,take.getStartNo());
                }
                log.info("队列中的数据 = {}",rows);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<DelayUser> getTestRow(LocalDate startDate, LocalDate endDate, int startNo) {
        List<DelayUser> list = new ArrayList<>();
        if (startNo == 0) {
            DelayUser u = new DelayUser();
            u.setUserId("000");
            u.setUserName("000");
            list.add(u);
        } else if (startNo == 1) {
            DelayUser u = new DelayUser();
            u.setUserId("111");
            u.setUserName("111");
            list.add(u);
        } else if (startNo == 2) {
            DelayUser u = new DelayUser();
            u.setUserId("222");
            u.setUserName("222");
            list.add(u);
        }
        return list;
    }


}
