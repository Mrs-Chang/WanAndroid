package com.chang.android.module_framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseViewBindActivity<VB : ViewBinding> : BaseActivity() {
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBindingClass()
        setContentView(binding.root)
        initView(savedInstanceState)
        initData()
    }

    private fun getViewBindingClass(): VB {
        // 获取 ViewBinding 的实际类型
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val clazz = type as Class<VB>
        // 通过反射获取静态的 inflate 方法
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, layoutInflater, null, false) as VB
    }

    abstract fun initView(savedInstanceState: Bundle?)

    open fun initData() {

    }

}