package com.chang.android.moudule_login.login

import com.chang.android.module_network.manager.ApiManager
import com.chang.android.module_network.repository.BaseRepository
import com.chang.android.moudule_login.model.User

class LoginRepository : BaseRepository() {
    /**
     * 登录
     * @param username  用户名
     * @param password  密码
     */
    suspend fun login(username: String, password: String): User? {
        return requestResponse {
            ApiManager.api.login(username, password)
        }
    }

    /**
     * 注册
     * @param username  用户名
     * @param password  密码
     * @param repassword  确认密码
     */
    suspend fun register(username: String, password: String, repassword: String): User? {
        return requestResponse {
            ApiManager.api.register(username, password, repassword)
        }
    }
}