package com.gre.lxl.httpStudy.retrofit.remote;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.gre.lxl.httpStudy.retrofit.register.RegisterSign;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author lxl
 * @date 2023/2/3 9:49
 */
@RetrofitClient(baseUrl = "${base.url}")
@RegisterSign(appId = "111", secretId = "2222")
public interface RemoteService {

    @GET("/hedging/queryHedgingPlanHeaderAmountList")
    String getTest();


    @Headers("Authorization:eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjE6MjE4OGY0OTNmNTQ3NDA2MjgxNmY4NTM1NWZhMDQ3YWIifQ.1MVbpumsa3jVJ0LYDTZbUAJIk_XL8oMuGbMGLXL08UOUrWOH7VitdhcrzzjF8xQE5qd4r-QuLv981n-6qpvlmg")
    @POST("/app/customer/evaluation/checkBillInfo")
    String getTest11(@Body Map<String , String> map);

}
