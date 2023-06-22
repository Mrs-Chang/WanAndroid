package com.chang.android.module_network.interceptor

import com.chang.android.module_network.constant.KEY_SET_COOKIE
import okhttp3.Interceptor
import okhttp3.Response

class CookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()

        val response = chain.proceed(newBuilder.build())
        val url = request.url.toString()
        val host = request.url.host

        if (response.headers(KEY_SET_COOKIE).isNotEmpty() && needSaveCookie(url)) {
            //TODO
        }
        //保存cookies
        return response
    }

    private fun needSaveCookie(url: String): Boolean {
        //TODO
        return false
    }
}