package com.chang.android.module_main.ui.system

import android.os.Bundle
import android.view.View
import com.chang.android.databinding.FragmentSystemBinding
import com.chang.android.module_framework.base.BaseMvvmFragmentV2
import com.chang.android.module_main.ui.system.viewmodel.SystemViewModel

class SystemFragment : BaseMvvmFragmentV2<FragmentSystemBinding, SystemViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding.textSystem.text = "SystemFragment"
    }
}