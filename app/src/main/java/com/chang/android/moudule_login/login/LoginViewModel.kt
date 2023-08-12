package com.chang.android.moudule_login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chang.android.module_framework.toast.TipsToast
import com.chang.android.module_framework.viewmodel.BaseViewModel
import com.chang.android.moudule_login.model.User

class LoginViewModel : BaseViewModel(){
    private val _loginLiveData = MutableLiveData<User?>()
    val loginLiveData: LiveData<User?> = _loginLiveData
    private val _registerLiveData = MutableLiveData<User?>()
    val registerLiveData: LiveData<User?> = _registerLiveData
    private val loginRepository by lazy { LoginRepository() }

    /**
     * 登录
     * @param username  用户名
     * @param password  密码
     * @return
     */
    fun login(username: String, password: String): LiveData<User?> {
        launchUI(errorBlock = { _, error ->
            TipsToast.showTips(error)
            _loginLiveData.value = null
        }) {
            val data = loginRepository.login(username, password)
            _loginLiveData.value = data
        }
        return loginLiveData
    }

    /**
     * 注册
     * @param username  用户名
     * @param password  密码
     * @param repassword  确认密码
     * @return
     */
    fun register(username: String, password: String, repassword: String): LiveData<User?> {
        launchUI(errorBlock = { _, error ->
            TipsToast.showTips(error)
            _registerLiveData.value = null
        }) {
            val data = loginRepository.register(username, password, repassword)
            _registerLiveData.value = data
        }
        return registerLiveData
    }

}