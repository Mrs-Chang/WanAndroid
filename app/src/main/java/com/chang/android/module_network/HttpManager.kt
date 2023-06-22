package com.chang.android.module_network

import android.util.Log
import com.chang.android.module_framework.AppHelper
import com.chang.android.module_framework.NetworkUtil
import com.chang.android.module_network.config.NetworkConfig
import com.chang.android.module_network.constant.BASE_URL
import com.chang.android.module_network.interceptor.CookiesInterceptor
import com.chang.android.module_network.interceptor.HeaderInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpManager {
    private val mRetrofit: Retrofit
    private val mNetworkConfig: NetworkConfig = NetworkConfig()

    init {
        mRetrofit = Retrofit.Builder()
            .client(initOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> create(apiService: Class<T>): T {
        return mRetrofit.create(apiService)
    }

    private fun initOkHttpClient(): OkHttpClient {
        val build = OkHttpClient.Builder()
            .connectTimeout(mNetworkConfig.connectTimeoutMs, TimeUnit.MILLISECONDS)
            .writeTimeout(mNetworkConfig.writeTimeoutMs, TimeUnit.MILLISECONDS)
            .readTimeout(mNetworkConfig.readTimeoutMs, TimeUnit.MILLISECONDS)
        // 添加参数拦截器
        build.addInterceptor(CookiesInterceptor())
        build.addInterceptor(HeaderInterceptor())
        // 日志拦截器 TODO 自定义Interceptor 处理请求报错的现场等
        val logInterceptor = HttpLoggingInterceptor { message: String ->
            Log.d("HttpManager", "logging_intercept: $message")
        }
        if (/*todo debug = ?*/ true) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        build.addInterceptor(logInterceptor)
        // 网络状态拦截
        build.addInterceptor(Interceptor { chain ->
            if (!NetworkUtil.isNetworkConnected(AppHelper.getApplication())) {
                throw IOException("网络异常，请尝试刷新")
            }
            chain.proceed(chain.request())
        })
        return build.build()
    }
}