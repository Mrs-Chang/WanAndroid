package com.chang.android.module_network.repository

import com.chang.android.module_network.error.ApiException
import com.chang.android.module_network.response.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

open class BaseRepository {
    /**
     * IO中处理请求
     */
    suspend fun <T> requestResponse(
        requestCall: suspend () -> BaseResponse<T>?
    ): T? {
        val response = withContext(Dispatchers.IO) {
            withTimeout(10 * 1000) {
                requestCall()
            }
        } ?: return null
        if (response.isFailed()) {
            throw ApiException(response.errorCode, response.errorMsg)
        }
        return response.data
    }
}