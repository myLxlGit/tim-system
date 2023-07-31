package com.gre.lxl.httpStudy.retrofit.remote;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.gre.lxl.httpStudy.retrofit.register.ApiSign;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author lxl
 * @date 2023/6/1 11:06
 */
@RetrofitClient(baseUrl = "${base.url}")
@ApiSign(appId = "63m5TAcNTHtLeZy7APOweUA1", secretId = "C6EB7AB6654BDBCEE05301C7040A74FC")
public interface rtService {

    @GET("erp/api/jm/io/querySalesActuralIoList")
    String querySalesActuralIoList(@Query("docId") long docId);


    @GET("erp/api/jm/invoice/queryValidArInvBillListByOmCon")
    String queryValidArInvBillListByOmCon(@Query("omContractId") long omContractId);


}
