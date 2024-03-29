package com.chang.android.moudule_login.di

import com.chang.android.moudule_login.login.ILoginRepository
import com.chang.android.moudule_login.login.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LoginModule {
    @Binds
    fun bindsLoginRepository(
        loginRepositoryImpl: LoginRepository
    ): ILoginRepository
}