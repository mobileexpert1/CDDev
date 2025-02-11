package com.composetut.ui.home.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectResponse(
    @SerializedName("response")
    val response: List<ResponseItem>?,
    @SerializedName("error")
    val error: error? = null,
    @SerializedName("isSuccess")
    val isSuccess: Boolean = false
)

@Serializable
data class ResponseItem(
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("mediaCount")
    val mediaCount: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("createDate")
    val createDate: String = "",
    @SerializedName("isPrimary")
    val isPrimary: Boolean=false,
    @SerializedName("email")
    val email: String?=null,
    @SerializedName("isAuthorize")
    var isAuthorize: Boolean=false,
    var isLast:Boolean=false,
    var isVisible:Boolean=false
)
@Serializable
data class error(
    @SerializedName("error")
    val error: String = "",
    @SerializedName("errorLocation")
    val errorLocation: String = "",
    @SerializedName("userMessage")
    val userMessage: String = ""
)

