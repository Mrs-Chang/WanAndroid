package com.chang.android.module_framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.chang.android.R
import com.chang.android.module_framework.toast.TipsToast
import com.chang.android.module_framework.utils.LoadingUtils

abstract class BaseFragment : Fragment() {
    private val dialogUtils by lazy {
        LoadingUtils(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
        initData()
    }

    open fun initData() {}

    abstract fun initView(view: View, savedInstanceState: Bundle?)

    /**
     * 加载中……弹框
     */
    fun showLoading() {
        showLoading(getString(R.string.module_base_default_loading))
    }

    /**
     * 加载提示框
     */
    fun showLoading(msg: String?) {
        dialogUtils.showLoading(msg)
    }

    /**
     * 关闭提示框
     */
    fun dismissLoading() {
        dialogUtils.dismissLoading()
    }

    /**
     * Toast
     * @param msg Toast内容
     */
    fun showToast(msg: String) {
        TipsToast.showTips(msg)
    }

    /**
     * Toast
     * @param resId 字符串id
     */
    fun showToast(@StringRes resId: Int) {
        TipsToast.showTips(resId)
    }

}