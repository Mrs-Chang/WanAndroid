package com.chang.android.module_framework.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chang.android.module_network.error.ExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(){
    fun launchUI(errorBlock: (Int?, String?) -> Unit, responseBlock: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            safeApiCall(errorBlock = errorBlock, responseBlock)
        }
    }

    /**
     * 需要运行在协程作用域中
     * @param errorBlock 错误回调
     * @param responseBlock 请求函数
     */
    suspend fun <T> safeApiCall(
        errorBlock: suspend (Int?, String?) -> Unit,
        responseBlock: suspend () -> T?
    ): T? {
        try {
            return responseBlock()
        } catch (e: Exception) {
            e.printStackTrace()
            val exception = ExceptionHandler.handleException(e)
            errorBlock(exception.errCode, exception.errMsg)
        }
        return null
    }
}