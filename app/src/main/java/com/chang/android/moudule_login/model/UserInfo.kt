package com.chang.android.moudule_login.model

import androidx.annotation.Keep
import com.chang.android.module_framework.base.ApiModel


@Keep
data class User(
    val id: Int? = 0,
    val username: String?,
    var nickname: String?,
    val token: String?,
    var icon: String? = "",
    val email: String? = "",
    var password: String?,
    var signature: String?,
    var sex: String?,
    var birthday: String? = ""
) : ApiModel() {
    fun getName(): String? {
        return if (!nickname.isNullOrEmpty()) {
            nickname
        } else {
            username
        }
    }
}