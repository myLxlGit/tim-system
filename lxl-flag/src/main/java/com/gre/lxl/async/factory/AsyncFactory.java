package com.gre.lxl.async.factory;

import com.gre.lxl.system.dto.Student;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 异步工厂（产生任务用）
 *
 * @author sumec
 */
public class AsyncFactory {

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final List<String> list) {

        return new TimerTask() {
            @Override
            public void run() {
                List<Student> collect = list.stream().map(item -> {
                    Student student = new Student();
                    student.setName(item);
                    student.setAge("100");
                    return student;
                }).collect(Collectors.toList());
                System.out.println(collect);

            }
        };
    }

    public static TimerTask clearInstance(final Consumer<String> consumer, final String instanceId) {
        return new TimerTask() {
            @Override
            public void run() {
                consumer.accept(instanceId);
            }
        };
    }

    public static TimerTask recordOper1(List<String> list) {
        return new TimerTask() {
            @Override
            public void run() {
                boolean ff = list.stream().anyMatch(p -> p.equals("ff"));
                System.out.println(ff);

            }
        };
    }
}
