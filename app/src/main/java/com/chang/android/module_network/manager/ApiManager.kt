package com.chang.android.module_network.manager

import com.chang.android.module_network.HttpManager
import com.chang.android.module_network.api.ApiInterface

object ApiManager {
    val api by lazy { HttpManager.create(ApiInterface::class.java) }
}