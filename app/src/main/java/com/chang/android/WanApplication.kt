package com.chang.android

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.chang.android.module_framework.helper.AppHelper
import com.chang.android.module_framework.manager.ActivityManager
import com.chang.android.module_framework.toast.TipsToast
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WanApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // App启动立即注册监听
        registerActivityLifecycle()
        AppHelper.init(this, BuildConfig.DEBUG)
        TipsToast.init(this)
    }

    /**
     * 注册Activity生命周期监听
     */
    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityManager.pop(activity)
            }

            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                ActivityManager.push(activity)
            }

            override fun onActivityResumed(activity: Activity) {
            }
        })
    }
}