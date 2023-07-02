package com.chang.android.module_framework.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.chang.android.R
import com.chang.android.module_framework.toast.TipsToast
import com.chang.android.module_framework.utils.LoadingUtils

abstract class BaseActivity : AppCompatActivity() {
    private val dialogUtils by lazy {
        LoadingUtils(this)
    }

    fun showLoading() {
        showLoading(getString(R.string.module_base_default_loading))
    }

    fun showLoading(msg: String?) {
        dialogUtils.showLoading(msg)
    }

    fun showLoading(@StringRes res: Int) {
        showLoading(getString(res))
    }

    fun dismissLoading() {
        dialogUtils.dismissLoading()
    }

    fun showToast(msg: String) {
        TipsToast.showTips(msg)
    }

    fun showToast(@StringRes resId: Int) {
        TipsToast.showTips(resId)
    }
}