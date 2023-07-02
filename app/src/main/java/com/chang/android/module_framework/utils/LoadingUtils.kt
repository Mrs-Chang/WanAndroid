package com.chang.android.module_framework.utils

import android.app.Activity
import android.content.Context
import com.chang.android.module_framework.dialog.CenterLoadingDialog

/**
 * 操作Loading Dialog
 */
class LoadingUtils(private val mContext: Context) {
    private val loadingDialog by lazy {
        CenterLoadingDialog(mContext)
    }

    fun showLoading(txt: String?) {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        if (!txt.isNullOrEmpty()) {
            loadingDialog.setTitle(txt as CharSequence)
        }
        if (mContext is Activity && mContext.isFinishing) {
            return
        }
        loadingDialog.show()
    }

    fun dismissLoading() {
        if (mContext is Activity && mContext.isFinishing) {
            return
        }
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }
}