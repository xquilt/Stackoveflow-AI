package com.polendina.soai.data.network

import com.google.common.collect.ImmutableMap
import com.polendina.soai.models.Answer
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface SoApi {
    @FormUrlEncoded
    @Headers("User-Agent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36")
    @POST("users/login/")
    fun authenticateUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @QueryMap queryMap: Map<String, String> = ImmutableMap.of("ssrc", "head", "returnurl", "https://stackoverflow.com")
    ): Call<ResponseBody>
    @POST("ai/search/next-step/")
    @FormUrlEncoded
    fun askQuestion(
        @Field("fkey") fkey: String,
        @Field("content") content: String,
        @Field("correlationId") correlationId: String,
        @Header("User-Agent") userAgent: String = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36"
    ): Call<Answer>
}