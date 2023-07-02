package com.chang.android.module_framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

@Deprecated("Use BaseMvvmFragmentV2 instead")
abstract class BaseMvvmFragment<VB : ViewBinding, VM : ViewModel> : BaseFragment() {
    protected lateinit var viewModel: VM
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?, b: Boolean): VB

    abstract fun getViewModelClass(): Class<VM>

}