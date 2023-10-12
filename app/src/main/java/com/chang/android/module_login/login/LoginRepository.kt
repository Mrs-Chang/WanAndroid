package com.chang.android.module_login.login

import com.chang.android.module_common.model.User
import com.chang.android.module_network.manager.ApiManager
import com.chang.android.module_network.repository.BaseRepository
import javax.inject.Inject

class LoginRepository @Inject constructor(): BaseRepository(), ILoginRepository {
    /**
     * 登录
     * @param username  用户名
     * @param password  密码
     */
    override suspend fun login(username: String, password: String): User? {
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
    override suspend fun register(username: String, password: String, repassword: String): User? {
        return requestResponse {
            ApiManager.api.register(username, password, repassword)
        }
    }
}