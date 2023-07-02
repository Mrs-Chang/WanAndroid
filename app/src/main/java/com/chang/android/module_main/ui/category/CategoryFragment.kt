package com.chang.android.module_main.ui.category

import android.os.Bundle
import android.view.View
import com.chang.android.databinding.FragmentCategoryBinding
import com.chang.android.module_framework.base.BaseMvvmFragmentV2
import com.chang.android.module_main.ui.category.viewmodel.CategoryViewModel

class CategoryFragment : BaseMvvmFragmentV2<FragmentCategoryBinding, CategoryViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding.tvCategory.text = "CategoryFragment"
    }
}