package com.chang.android

import android.app.Application
import com.chang.android.module_framework.AppHelper

class WanApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AppHelper.init(this, BuildConfig.DEBUG)
    }
}