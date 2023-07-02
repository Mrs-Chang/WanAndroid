package com.chang.android.module_main.ui.home

import android.os.Bundle
import android.view.View
import com.chang.android.databinding.FragmentHomeBinding
import com.chang.android.module_framework.base.BaseMvvmFragmentV2
import com.chang.android.module_main.ui.mine.viewmodel.MineViewModel

class HomeFragment : BaseMvvmFragmentV2<FragmentHomeBinding, MineViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding.textView.text = "HomeFragment"
    }
}