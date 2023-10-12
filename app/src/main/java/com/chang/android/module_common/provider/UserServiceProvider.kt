package com.chang.android.module_common.provider

import androidx.lifecycle.LiveData
import com.chang.android.module_common.model.User
import com.chang.android.module_common.service.IUserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserServiceProvider @Inject constructor() {
    @Inject
    lateinit var userService: IUserService

    /**
     * 是否登录
     * @return Boolean
     */
    fun isLogin(): Boolean {
        return userService.isLogin()
    }

    /**
     * 获取用户信息
     * @return User or null
     */
    fun getUserInfo(): User? {
        return userService.getUserInfo()
    }

    /**
     * 保存用户信息
     * @param user
     */
    fun saveUserInfo(user: User?) {
        userService.saveUserInfo(user)
    }

    /**
     * 清除用户信息
     */
    fun clearUserInfo() {
        userService.clearUserInfo()
    }

    /**
     * 获取User信息LiveData
     */
    fun getUserLiveData(): LiveData<User?>? {
        return userService.getUserLiveData()
    }

    /**
     * 保存用户手机号码
     * @param phone
     */
    fun saveUserPhone(phone: String?) {
        userService.saveUserPhone(phone)
    }

    /**
     * 保存用户手机号码
     * @return phone
     */
    fun getUserPhone(): String? {
        return userService.getUserPhone()
    }
}