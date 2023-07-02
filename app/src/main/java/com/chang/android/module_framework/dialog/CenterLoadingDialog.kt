package com.chang.android.module_framework.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.annotation.StyleRes
import com.chang.android.R
import com.chang.android.databinding.MoudleBaseDialogLoadingBinding

/**
 * 通用加载中弹窗
 */
class CenterLoadingDialog(
    context: Context,
    @StyleRes themeId: Int = R.style.Module_Base_Loading_Dialog
) :
    Dialog(context, themeId) {
    private val binding: MoudleBaseDialogLoadingBinding
    private var animation: Animation? = null

    init {
        // 不能用这个，因为这个layoutInflater是全局的，会导致context泄漏？layoutInflater通过window拿的？
        // binding = MoudleBaseDialogLoadingBinding.inflate(layoutInflater)
        binding = MoudleBaseDialogLoadingBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        initAnim()
    }

    /**
     * 初始化动画
     * 以中心点为旋转点，旋转360度
     */
    private fun initAnim() {
        animation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        animation?.duration = 2000
        animation?.repeatCount = -1
        animation?.fillAfter = true
    }

    override fun show() {
        super.show()
        binding.ivImage.startAnimation(animation)
    }

    override fun dismiss() {
        super.dismiss()
        binding.ivImage.clearAnimation()
    }

    override fun setTitle(title: CharSequence?) {
        if (!title.isNullOrEmpty()) {
            binding.tvMsg.text = title
        }
    }
}