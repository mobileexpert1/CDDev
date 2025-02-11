package com.composetut.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composetut.data.MyPreference
import com.composetut.data.MyResults
import com.composetut.data.PreferenceKey
import com.composetut.data.ServerUtils
import com.composetut.network.ApiService
import com.composetut.network.ApiServiceImpl
import com.composetut.ui.home.model.ProjectRequest
import com.composetut.ui.home.model.ProjectResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import retrofit2.http.Body

class HomeVm(val repo: ApiService):ViewModel() {

    var _stateFlow= MutableStateFlow<MyResults<ProjectResponse>>(MyResults.IsLoading())
    val stateFlow:StateFlow<MyResults<ProjectResponse>> =_stateFlow

    fun getDetails(request: ProjectRequest){
        _stateFlow.value=MyResults.IsLoading()
        viewModelScope.launch {
            try {
                val result = repo.getProjects(request)
                Log.e("homeresults", "homescreen ${result}")

                if (result.isSuccessful) {
                    _stateFlow.value = MyResults.IsSuccess(result.body())
                } else {
                    _stateFlow.value = MyResults.IsError(result.message())
                }
            }catch (e:Exception){
                _stateFlow.value = MyResults.IsError(e.message.toString())
                Log.e("homeresults", "error ${e.message}")
            }
            fetchData(request)
        }

    }

    suspend fun fetchData(request: ProjectRequest) {
//        val token=MyPreference.getValue(PreferenceKey.TOKEN)
//        val client = HttpClient(Android) {
//            install(ContentNegotiation) {
//                json(Json {
//                    prettyPrint = true
//                    isLenient = true
//                })
//            }
//            defaultRequest {
//                url(ServerUtils.BASE_URL)
//                headers {
//                    append("Authorization", "Bearer $token")
//                    append("accept", "text/plain")
//                    append("Content-Type", "application/json")
//                }
//            }
//        }

//        try {
//            val response = client.get("https://jsonplaceholder.typicode.com/posts")
//
//            val result=repo.getProject(request)
//            val response= client.post(ServerUtils.ALL_PROJECTS) {
//                setBody(request)
//                headers {
////                    append("Authorization", "Bearer $token")
////                    append("accept", "text/plain")
////                    append("Content-Type", "application/json")
//
//                }
//            }

         //   Log.e("reponse","ncdjncjd ${result}")
          //  _stateFlow.value = MyResults.IsSuccess(result)
//        } catch (e: Exception) {
//            println("Error: ${e.message}")
//        } finally {
//          //  client.close()
//        }
    }

}
