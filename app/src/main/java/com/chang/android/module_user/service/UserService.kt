package com.chang.android.module_user.service

import android.util.Log
import androidx.lifecycle.LiveData
import com.chang.android.module_common.model.User
import com.chang.android.module_common.service.IUserService
import javax.inject.Inject

class UserService @Inject constructor() : IUserService {
    override fun isLogin(): Boolean {
        return false
    }

    override fun getUserInfo(): User? {
        return null
    }

    override fun saveUserInfo(user: User?) {
        Log.d("GaoChang", "saveUserInfo: ")
    }

    override fun clearUserInfo() {
    }

    override fun getUserLiveData(): LiveData<User?>? {
        return null
    }

    override fun saveUserPhone(phone: String?) {
    }

    override fun getUserPhone(): String? {
        return null
    }
}