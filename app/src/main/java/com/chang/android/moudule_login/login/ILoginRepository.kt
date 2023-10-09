package com.chang.android.moudule_login.login

import com.chang.android.moudule_login.model.User

interface ILoginRepository {
    suspend fun login(username: String, password: String): User?
    suspend fun register(username: String, password: String, repassword: String): User?
}