package com.chang.android.moudule_login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.chang.android.MainActivity
import com.chang.android.R
import com.chang.android.databinding.ActivityLoginBinding
import com.chang.android.module_framework.base.BaseMvvmActivity
import com.chang.android.module_framework.ext.onClick
import com.chang.android.module_framework.ext.textChangeFlow
import com.chang.android.module_framework.toast.TipsToast
import com.chang.android.module_framework.utils.getColorFromResource
import com.chang.android.module_framework.utils.getStringFromResource
import com.chang.android.module_framework.utils.immerse
import com.chang.android.moudule_login.login.LoginViewModel
import com.chang.android.moudule_login.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginActivity : BaseMvvmActivity<ActivityLoginBinding, LoginViewModel>() {

    private var isShowPassword = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        immerse()
        initData()
    }

    override fun initView(savedInstanceState: Bundle?) {
        initAgreement()
        initListener()
    }

    private fun initData() {
        viewModel.loginLiveData.observe(this) { user ->
            //登录成功
            dismissLoading()
            user?.let {
                //TODO save user info
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun initAgreement() {
        val agreement = getStringFromResource(R.string.login_agreement)
        binding.cbAgreement.movementMethod = LinkMovementMethod.getInstance()
        val spaBuilder = SpannableStringBuilder(agreement)
        val privacySpan = getStringFromResource(R.string.login_privacy_agreement)
        val serviceSpan = getStringFromResource(R.string.login_user_agreement)
        spaBuilder.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    (widget as TextView).highlightColor = getColorFromResource(R.color.transparent)
                    //TODO
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }

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
                    //TODO
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }

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
    }

    private fun initListener() {
        binding.ivPasswordToggle.onClick {
            setPasswordHide()
        }
        binding.tvLogin.onClick {
            toLogin()
        }
        binding.tvRegister.onClick {
            RegisterActivity.start(this)
        }
        setEditTextChange(binding.etPhone)
        setEditTextChange(binding.etPassword)
        binding.cbAgreement.setOnCheckedChangeListener { _, _ ->
            updateLoginState()
        }
    }

    private fun toLogin() {
        val userName = binding.etPhone.text?.trim()?.toString()
        val password = binding.etPassword.text?.trim()?.toString()

        if (userName.isNullOrEmpty() || userName.length < 11) {
            TipsToast.showTips(getStringFromResource(R.string.error_phone_number))
            return
        }
        if (password.isNullOrEmpty()) {
            TipsToast.showTips(R.string.error_input_password)
            return
        }
        if (!binding.cbAgreement.isChecked) {
            TipsToast.showTips(R.string.tips_read_user_agreement)
            return
        }
        showLoading()
        viewModel.login(userName, password)
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
        val phoneEnable = !phone.isNullOrEmpty() && phone.length == 11
        val password = binding.etPassword.text.toString()
        val passwordEnable = !password.isNullOrEmpty()
        val agreementEnable = binding.cbAgreement.isChecked

        binding.tvLogin.isSelected = phoneEnable && passwordEnable && agreementEnable
    }


    private fun setPasswordHide() {
        isShowPassword = !isShowPassword
        if (isShowPassword) {
            binding.ivPasswordToggle.setImageResource(R.mipmap.ic_psw_invisible)
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.ivPasswordToggle.setImageResource(R.mipmap.ic_psw_visible)
        }
        binding.etPassword.setSelection(binding.etPassword.length())
    }
}