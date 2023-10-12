package com.chang.android.module_user.di

import com.chang.android.module_common.service.IUserService
import com.chang.android.module_user.service.UserService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface UserModule {
    @Binds
    fun bindsUserService(
        userServiceImpl: UserService
    ): IUserService
}