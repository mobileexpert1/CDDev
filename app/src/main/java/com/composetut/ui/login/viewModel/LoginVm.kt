package com.composetut.ui.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composetut.data.MyPreference
import com.composetut.data.MyResults
import com.composetut.data.PreferenceKey
import com.composetut.data.ServerUtils
import com.composetut.network.ApiService
import com.composetut.ui.login.model.LoginRequest
import com.composetut.ui.login.model.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.request
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class LoginVm(val repo: ApiService) : ViewModel() {
    var _loginMutable = MutableStateFlow<MyResults<LoginResponse>>(MyResults.IsLoading())
    var loginLive: StateFlow<MyResults<LoginResponse>> = _loginMutable


    fun fetchData(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val token = MyPreference.getValue(PreferenceKey.TOKEN)

            val client = HttpClient(Android) {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }

                defaultRequest {
                    url(ServerUtils.AUTH_BASE_URL)
                    headers {
                        append("accept", "text/plain")
                        append("Content-Type", "application/json")
                        append("Authorization", "Bearer $token")
                    }
                }
            }

            try {

                val result= client.post(ServerUtils.LOGIN_URL){
                    setBody(loginRequest)
                }
                Log.e("ktorStatus", "Results ${result.body<String>()}")
            } catch (e: Exception) {
                Log.e("ktorStatus", "Error ${e.message}")
            }
        }
    }
}

//var _loginMutable = MutableStateFlow<MyResults<LoginResponse>>(MyResults.IsLoading())
//var loginLive: StateFlow<MyResults<LoginResponse>> = _loginMutable
//var job: Job? = null
//fun login(loginRequest: LoginRequest) {
//    job = viewModelScope.launch {
//        _loginMutable.value = MyResults.IsLoading()
//        val result = repo.login(loginRequest)
//        if (result.isSuccessful) {
//            _loginMutable.value = MyResults.IsSuccess(result.body())
//            job?.cancel()
//        } else {
//            _loginMutable.value = MyResults.IsError("Something went wrong")
//            job?.cancel()
//        }
////            Log.e("djncjdc", "results ${result}")
//    }
//}