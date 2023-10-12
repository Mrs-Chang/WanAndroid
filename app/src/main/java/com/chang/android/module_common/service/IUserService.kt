package com.chang.android.module_common.service

import androidx.lifecycle.LiveData
import com.chang.android.module_common.model.User

interface IUserService {
    /**
     * 是否登录
     * @return Boolean
     */
    fun isLogin(): Boolean

    /**
     * 获取用户信息
     * @return User or null
     */
    fun getUserInfo(): User?

    /**
     * 保存用户信息
     * @param user
     */
    fun saveUserInfo(user: User?)

    /**
     * 清除用户信息
     */
    fun clearUserInfo()

    /**
     * 获取User信息LiveData
     */
    fun getUserLiveData(): LiveData<User?>?

    /**
     * 保存用户手机号码
     * @param phone
     */
    fun saveUserPhone(phone: String?)

    /**
     * 保存用户手机号码
     * @return phone
     */
    fun getUserPhone(): String?
}