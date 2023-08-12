package com.chang.android.module_network.response

data class BaseResponse<out T>(
    val data: T?,
    val errorCode: Int = 0,
    val errorMsg: String = ""
) {
    fun isFailed(): Boolean {
        return errorCode != 0
    }
}
