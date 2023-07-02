package com.chang.android

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.chang.android.databinding.ActivityMainBinding
import com.chang.android.module_framework.base.BaseViewBindActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseViewBindActivity<ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        //NavigationUI.setupWithNavController(bottomNavigationView, navController)
        bottomNavigationView.setupWithNavController(navController) // 与上面一行代码等价
    }
}