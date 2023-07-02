package com.chang.android.module_framework.toast

import android.app.Application
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.chang.android.R
import com.chang.android.databinding.MoudleBaseWidgetTipsToastBinding

object TipsToast {
    //Toast，通常推荐使用Application Context，除非你有特定的需要使用Activity Context的理由。
    // 使用Application Context可以避免因为Activity的销毁而可能产生的内存泄漏问题。
    private lateinit var context: Application

    private val binding: MoudleBaseWidgetTipsToastBinding by lazy {
        MoudleBaseWidgetTipsToastBinding.inflate(LayoutInflater.from(context), null, false)
    }

    private var toast: Toast? = null

    fun init(context: Application) {
        TipsToast.context = context
    }

    fun showTips(@StringRes stringId: Int) {
        val msg = context.getString(stringId)
        showTipsImpl(
            msg,
            Toast.LENGTH_SHORT
        )
    }

    fun showTips(msg: String?) {
        showTipsImpl(
            msg,
            Toast.LENGTH_SHORT
        )
    }

    fun showTips(msg: String?, duration: Int) {
        showTipsImpl(msg, duration)
    }

    fun showTips(@StringRes stringId: Int, duration: Int) {
        val msg = context.getString(stringId)
        showTipsImpl(msg, duration)
    }

    fun showSuccessTips(msg: String?) {
        showTipsImpl(
            msg,
            Toast.LENGTH_SHORT,
            R.mipmap.module_base_widget_toast_success
        )
    }

    fun showSuccessTips(@StringRes stringId: Int) {
        val msg = context.getString(stringId)
        showTipsImpl(
            msg,
            Toast.LENGTH_SHORT,
            R.mipmap.module_base_widget_toast_success
        )
    }

    fun showWarningTips(msg: String?) {
        showTipsImpl(
            msg,
            Toast.LENGTH_SHORT,
            R.mipmap.module_base_widget_toast_warning
        )
    }

    fun showWarningTips(@StringRes stringId: Int) {
        val msg = context.getString(stringId)
        showTipsImpl(
            msg,
            Toast.LENGTH_SHORT,
            R.mipmap.module_base_widget_toast_warning
        )
    }

    private fun showTipsImpl(
        msg: String?,
        duration: Int,
        @DrawableRes drawableId: Int = 0,
    ) {
        if (msg.isNullOrEmpty()) {
            return
        }
        toast?.let {
            it.cancel()
            toast = null
        }
        toast = Toast(context)
        //TODO view方法在被遗弃，可以使用SpannableString设计样式 or Snackbar
        toast!!.view = binding.root
        binding.tipToastTxt.text = msg
        binding.tipToastTxt.setCompoundDrawablesWithIntrinsicBounds(
            drawableId,
            0,
            0,
            0
        )
        toast!!.setGravity(Gravity.CENTER, 0, 0)
        toast!!.duration = duration
        toast!!.show()
    }
}