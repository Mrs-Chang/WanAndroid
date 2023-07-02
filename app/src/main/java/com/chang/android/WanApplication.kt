package com.chang.android

import android.app.Application
import com.chang.android.module_framework.helper.AppHelper
import com.chang.android.module_framework.toast.TipsToast

class WanApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AppHelper.init(this, BuildConfig.DEBUG)
        TipsToast.init(this)
    }
}