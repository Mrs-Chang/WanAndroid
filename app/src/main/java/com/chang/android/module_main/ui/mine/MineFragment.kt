package com.chang.android.module_main.ui.mine

import android.os.Bundle
import android.view.View
import com.chang.android.databinding.FragmentMineBinding
import com.chang.android.module_framework.base.BaseMvvmFragmentV2
import com.chang.android.module_main.ui.mine.viewmodel.MineViewModel

class MineFragment : BaseMvvmFragmentV2<FragmentMineBinding, MineViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding.textView.text = "MineFragment"
    }
}