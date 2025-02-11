package com.composetut.data

import com.composetut.ui.home.HomeVm
import com.composetut.ui.login.viewModel.LoginVm
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinCompo:KoinComponent {
    val loginVM:LoginVm by inject()
    val homeVm:HomeVm by inject()
}