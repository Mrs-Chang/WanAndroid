package com.chang.android.module_framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * 通过反射的方式简化模版
 */
abstract class BaseMvvmFragmentV2<VB : ViewBinding, VM : ViewModel> : BaseFragment() {
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBindingClass()
        return binding.root
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
        // 调用静态方法，object 传 null，因为是静态方法，不需要对象实例
        return method.invoke(null, layoutInflater, null, false) as VB
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    private fun <VM : ViewModel> getViewModelClass(): Class<VM> {
        //VB=>[0], VM=>[1]
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
        return type as Class<VM>
    }
}