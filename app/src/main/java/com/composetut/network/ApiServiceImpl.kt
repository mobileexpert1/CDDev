package com.composetut.network

import android.util.Log
import com.composetut.data.ServerUtils
import com.composetut.ui.home.model.ProjectRequest
import com.composetut.ui.home.model.ProjectResponse
import com.composetut.ui.login.model.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body

import io.ktor.client.request.post
import io.ktor.client.request.setBody


class ApiServiceImpl(private val client: HttpClient)  {
     suspend fun getProject(request: ProjectRequest): ProjectResponse {
       return client.post(ServerUtils.ALL_PROJECTS){
            setBody(request)
        }.body()

     }

     suspend fun login(request: LoginRequest): String {
        val response = client.post(ServerUtils.LOGIN_URL) {
            setBody(request)
        }
        return response.body()
    }
}
