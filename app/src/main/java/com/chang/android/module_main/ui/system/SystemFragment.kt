package com.chang.android.module_main.ui.system

import android.os.Bundle
import android.view.View
<<<<<<< HEAD
import android.view.ViewGroup
import com.chang.android.R

class SystemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_system, container, false)
=======
import com.chang.android.databinding.FragmentSystemBinding
import com.chang.android.module_framework.base.BaseMvvmFragmentV2
import com.chang.android.module_main.ui.system.viewmodel.SystemViewModel

class SystemFragment : BaseMvvmFragmentV2<FragmentSystemBinding, SystemViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding.textSystem.text = "SystemFragment"
>>>>>>> develop
    }
}