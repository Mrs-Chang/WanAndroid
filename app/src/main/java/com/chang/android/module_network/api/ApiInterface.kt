package com.chang.android.module_network.api

import com.chang.android.module_network.response.BaseResponse
import com.chang.android.moudule_login.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    /**
     * 登录
     * @param username  用户名
     * @param password  密码
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): BaseResponse<User?>?

    /**
     * 注册
     * @param username  用户名
     * @param password  密码
     * @param repassword  确认密码
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): BaseResponse<User?>?
}