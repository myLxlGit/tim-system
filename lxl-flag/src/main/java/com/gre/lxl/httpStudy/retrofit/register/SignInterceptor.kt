package com.gre.lxl.httpStudy.retrofit.register

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor
import com.google.common.hash.Hashing
import com.gre.lxl.common.exception.CustomException
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.charset.StandardCharsets

@Component
class SignInterceptor : BasePathMatchInterceptor() {

    private val API_BODY_SIGN = "Body-Sign"

    private val API_APP_ID = "App-Id"

    private val API_REQ_TIME = "Req-Time"

    private var appId: String? = null
    private var secretId: String? = null
    fun setAppId(accessKeyId: String?) {
        appId = accessKeyId
    }

    fun setSecretId(accessKeySecret: String?) {
        secretId = accessKeySecret
    }

    @Throws(IOException::class)
    public override fun doIntercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val method = request.method()
        if (appId.isNullOrBlank() || secretId.isNullOrBlank()) {
            throw CustomException("接口调用初始化失败：参数不存在")
        }
        val url = request.url()
        val currentTimeMillis = System.currentTimeMillis().toString()
        var result = ""
        if (method == "GET" || method == "DELETE") {
            val params = mutableListOf(API_APP_ID to appId, API_REQ_TIME to currentTimeMillis)
            url.queryParameterNames().forEach {
                params.add(it to url.queryParameter(it))
            }
            result = params.sortedBy { it.first }.joinToString(separator = "") { it.first + it.second } + secretId

        } else if (method == "POST" || method == "PUT") {

            val contentType = request.body()?.contentType().toString()
            if (contentType.contains("application/json")) {
                val buffer = Buffer()
                request.body()?.writeTo(buffer)
                val ins = buffer.inputStream()
                val available = ins.available()
                val body = ByteArray(available)
                ins.read(body)
                result = String(body, StandardCharsets.UTF_8) + API_APP_ID + appId + API_REQ_TIME + currentTimeMillis + secretId
            } else if (contentType.contains("application/x-www-form-urlencoded")){
                val params = mutableListOf(API_APP_ID to appId, API_REQ_TIME to currentTimeMillis)
                val body = request.body()
                if (body is FormBody) {
                    for (i in 0 until body.size()) {
                        params.add(body.encodedName(i) to body.encodedValue(i))
                    }
                }
                result = params.sortedBy { it.first }.joinToString(separator = "") { it.first + it.second } + secretId
            } else {
                throw CustomException("不支持的请求方式,仅支持请求头application/x-www-form-urlencoded或application/json")
            }
        }
        val sign = Hashing.md5().hashString(result, StandardCharsets.UTF_8).toString()
        val newReq = request.newBuilder()
            .addHeader(API_APP_ID, appId!!)
            .addHeader(API_REQ_TIME, currentTimeMillis)
            .addHeader(API_BODY_SIGN, sign).build()

        return chain.proceed(newReq)
    }
}