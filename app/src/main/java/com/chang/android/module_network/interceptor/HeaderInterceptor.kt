package com.chang.android.module_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.addHeader("Content-type", "application/json; charset=utf-8")
        //User-Agent, Referer ...
        val host = request.url.host
        val url = request.url.toString()
        //给有需要的接口添加Cookies
        if (!host.isNullOrEmpty() && needAddCookies(url)) {
            //TODO
        }
        return chain.proceed(newBuilder.build())
    }

    private fun needAddCookies(url: String): Boolean {
        //TODO
        return false
    }
}