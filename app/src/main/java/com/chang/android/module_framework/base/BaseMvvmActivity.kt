package com.chang.android.module_framework.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmActivity<VB : ViewBinding, VM : ViewModel> : BaseViewBindActivity<VB>() {
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        super.onCreate(savedInstanceState)
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
        return type as Class<VM>
    }
}