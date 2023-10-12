package com.chang.android.module_login.register

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.chang.android.R
import com.chang.android.databinding.ActivityRegisterBinding
import com.chang.android.module_framework.base.BaseMvvmActivity
import com.chang.android.module_framework.ext.onClick
import com.chang.android.module_framework.ext.textChangeFlow
import com.chang.android.module_framework.toast.TipsToast
import com.chang.android.module_framework.utils.StatusBarSettingHelper
import com.chang.android.module_framework.utils.getColorFromResource
import com.chang.android.module_framework.utils.getStringFromResource
import com.chang.android.module_login.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegisterActivity : BaseMvvmActivity<ActivityRegisterBinding, LoginViewModel>() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarSettingHelper.immersiveStatusBar(
            this@RegisterActivity,
            dark = false,
            fitSystemWindows = true
        )
    }

    override fun initView(savedInstanceState: Bundle?) {
        initAgreement()
        initListener()
    }

    private fun initListener() {
        binding.tvRegister.onClick {
            toRegister()
        }

        setEditTextChange(binding.etPhone)
        setEditTextChange(binding.etPassword)
        setEditTextChange(binding.etRepassword)
        binding.cbAgreement.setOnCheckedChangeListener { _, _ ->
            updateLoginState()
        }
    }

    private fun toRegister() {
        val userName = binding.etPhone.text?.trim()?.toString()
        val password = binding.etPassword.text?.trim()?.toString()
        val repassword = binding.etRepassword.text?.trim()?.toString()

        if (userName.isNullOrEmpty() || userName.length < 11) {
            TipsToast.showTips(getStringFromResource(R.string.error_phone_number))
            return
        }
        if (password.isNullOrEmpty() || repassword.isNullOrEmpty() || password != repassword) {
            TipsToast.showTips(R.string.error_double_password)
            return
        }
        if (!binding.cbAgreement.isChecked) {
            TipsToast.showTips(R.string.tips_read_user_agreement)
            return
        }
        showLoading()
        viewModel.register(userName, password, repassword).observe(this) { user ->
            dismissLoading()
            user?.let {
                TipsToast.showTips(R.string.success_register)
                finish()
            } ?: kotlin.run {

            }
        }
    }

    private fun setEditTextChange(editText: EditText) {
        editText.textChangeFlow()
            .debounce(300)
            .flowOn(Dispatchers.IO)
            .onEach {
                updateLoginState()
            }
            .launchIn(lifecycleScope)
    }

    /**
     * 更新登录按钮状态
     */
    private fun updateLoginState() {
        val phone = binding.etPhone.text.toString()
        val phoneEnable = phone.isNotEmpty() && phone.length == 11
        val password = binding.etPassword.text.toString()
        val repassword = binding.etRepassword.text.toString()
        val passwordEnable = password.isNotEmpty() && repassword.isNotEmpty()
        val agreementEnable = binding.cbAgreement.isChecked

        binding.tvRegister.isSelected = phoneEnable && passwordEnable && agreementEnable
    }


    /**
     * 初始化协议点击
     */
    private fun initAgreement() {
        val agreement = getStringFromResource(R.string.login_agreement)
        try {
            binding.cbAgreement.movementMethod = LinkMovementMethod.getInstance()
            val spaBuilder = SpannableStringBuilder(agreement)
            val privacySpan = getStringFromResource(R.string.login_privacy_agreement)
            val serviceSpan = getStringFromResource(R.string.login_user_agreement)
            spaBuilder.setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        (widget as TextView).highlightColor = getColorFromResource(R.color.transparent)
                        //PrivacyPolicyActivity.start(this@RegisterActivity)
                    }

                    @RequiresApi(Build.VERSION_CODES.M)
                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.color = getColorFromResource(R.color.color_0165b8)
                        ds.isUnderlineText = false
                        ds.clearShadowLayer()
                    }
                },
                spaBuilder.indexOf(privacySpan),
                spaBuilder.indexOf(privacySpan) + privacySpan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spaBuilder.setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        (widget as TextView).highlightColor = getColorFromResource(R.color.transparent)
                        //PrivacyPolicyActivity.start(this@RegisterActivity)
                    }

                    @RequiresApi(Build.VERSION_CODES.M)
                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.color = getColorFromResource(R.color.color_0165b8)
                        ds.isUnderlineText = false
                        ds.clearShadowLayer()
                    }
                },
                spaBuilder.indexOf(serviceSpan),
                spaBuilder.indexOf(serviceSpan) + serviceSpan.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            binding.cbAgreement.setText(spaBuilder, TextView.BufferType.SPANNABLE)
        } catch (e: Exception) {
            binding.cbAgreement.text = agreement
        }
    }
}