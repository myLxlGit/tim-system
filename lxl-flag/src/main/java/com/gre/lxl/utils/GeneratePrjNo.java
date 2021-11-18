package com.gre.lxl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis自动生成规则基建项目编号
 *
 * @author lxl
 * @date 2020/12/24 10:07
 */
@Service
public class GeneratePrjNo {

    //工程编号
    private static final String GC_CODE = "TK-";
    // SUMEC编号
    private static final String CODE = "-ANGLE";
    // 最大流水号
    private static final Long MAX_CODE = 999L;
    // 一个月（31天）的小时数
    private static final Long HOURS_IN_ONE_MONTH = 24*31L;

    @Autowired
    private StringRedisTemplate redis;

    public String getPrjNo(){
        // 流水号
        Long suffix = 0L;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(new Date());
        String prefix = GC_CODE + time + CODE;

        //前缀存在
        if (redis.hasKey(prefix)) {

            suffix = redis.opsForValue().increment(prefix,1L);

            if (suffix > MAX_CODE) {
                throw new RuntimeException("项目编号已超过最大限制" + MAX_CODE + "，请联系管理员");
            }
        } else {
            try {
                redis.opsForValue().set(prefix, String.valueOf(suffix));
                redis.expire(prefix, HOURS_IN_ONE_MONTH, TimeUnit.HOURS);
            }
            catch (Exception e) {
                throw new RuntimeException("项目编号写入REDIS时失败，请联系管理员");
            }
        }
        return prefix + String.format("%03d", suffix);
    }

}
