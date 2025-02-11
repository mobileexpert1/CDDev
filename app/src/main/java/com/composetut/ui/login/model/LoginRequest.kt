package com.composetut.ui.login.model

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequest(
    val email:String="",
    var password:String=""
)
