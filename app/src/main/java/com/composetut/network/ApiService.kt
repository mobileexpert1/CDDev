package com.composetut.network
import com.composetut.data.ServerUtils
import com.composetut.ui.home.model.ProjectRequest
import com.composetut.ui.home.model.ProjectResponse
import com.composetut.ui.login.model.LoginRequest
import com.composetut.ui.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
   suspend fun getDetails():String
   @POST(ServerUtils.LOGIN_URL)
   suspend fun login( @Body body: LoginRequest): Response<LoginResponse>

   @POST(ServerUtils.ALL_PROJECTS)
   suspend fun getProjects(
      @Body request: ProjectRequest
   ):Response<ProjectResponse>

}