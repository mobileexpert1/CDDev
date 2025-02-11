package com.composetut.ui.login.model


import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("backgroundImage")
                    val backgroundImage: String = "",
                    @SerializedName("logo")
                    val logo: String = "",
                    @SerializedName("expiration")
                    val expiration: String = "",
                    @SerializedName("profileImage")
                    val profileImage: String? = null,
                    @SerializedName("accessToken")
                    val accessToken: String = "",
                    @SerializedName("refreshToken")
                    val refreshToken: String = "",
                    @SerializedName("backgroundTheme")
                    val backgroundTheme: String = "")

data class LoginResponse(@SerializedName("response")
                         val response: Response,
                         @SerializedName("error")
                         val error: Error,
                         @SerializedName("isSuccess")
                         val isSuccess: Boolean = false)

data class Error(@SerializedName("userMessage")
                 val userMessage: String = "",
                 @SerializedName("developerMessage")
                 val developerMessage: String = "",
                 @SerializedName("error")
                 val error: String = "",
                 @SerializedName("errorLocation")
                 val errorLocation: String = "")


