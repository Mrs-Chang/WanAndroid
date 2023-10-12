package com.chang.android.module_login.login

import com.chang.android.module_common.model.User


interface ILoginRepository {
    suspend fun login(username: String, password: String): User?
    suspend fun register(username: String, password: String, repassword: String): User?
}