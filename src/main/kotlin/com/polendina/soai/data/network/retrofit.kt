package com.polendina.soai.data.network

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val cookiesList: MutableList<Cookie> = mutableListOf()
private val cookieJar = object: CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookiesList.addAll(cookies)
    }
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return (cookiesList)
    }
}

internal val retrofit = Retrofit.Builder()
    .baseUrl("https://stackoverflow.com/")
    .client(
        OkHttpClient()
            .newBuilder()
            .followRedirects(true)
            .cookieJar(cookieJar)
            .build()
    )
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(SoApi::class.java)