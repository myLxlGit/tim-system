package com.gre.lxl.httpStudy.retrofit.register;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import com.google.common.hash.Hashing;
import com.gre.lxl.common.exception.CustomException;
import com.gre.lxl.common.util.StringUtils;
import lombok.*;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author lxl
 * @date 2023/2/3 9:43
 */
@Setter
public class EyeSkySignInterceptor extends BasePathMatchInterceptor {

    private String appId;
    private String secretId;

    @Override
    public Response doIntercept(Interceptor.Chain chain) throws IOException {

        String auth = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjE6ZDY4ZWZlN2VhZWU3NDllODhhZWJlYjQ0MjEwMWRjNmIifQ.tUHTqmQOUvxyiEshm3I730IaqUW18VPffa_UnoY14G0lEdORBKTB7W4S4sZkJZmu0Skc246nA6wu1zWYdD6HWQ";

        Request request = chain.request();
        System.out.println("appId=" + appId);
        System.out.println("secretId=" + secretId);
//        request.newBuilder().addHeader()
        Request newReq = request.newBuilder()
                .addHeader("accessKeyId", appId)
                .addHeader("accessKeySecret", secretId)
                .addHeader("Authorization", auth)
                .build();
        return chain.proceed(newReq);
    }

}
