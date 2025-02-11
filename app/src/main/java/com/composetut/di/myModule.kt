package com.composetut.di

import com.composetut.data.MyPreference
import com.composetut.data.PreferenceKey.TOKEN
import com.composetut.data.ServerUtils
import com.composetut.network.ApiService
import com.composetut.network.ApiServiceImpl
import com.composetut.network.MyHttpClient
import com.composetut.ui.home.HomeVm
import com.composetut.ui.login.viewModel.LoginVm
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun myModule() = module {
    single<HttpClient> {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }


            defaultRequest {
                url(ServerUtils.BASE_URL)
                headers {
                    append("Accept", "application/json")
                    append("Content-Type", "application/json")
                    val token = getAccessToken() // Replace with your token retrieval logic
                    if (token.isNotEmpty()) {
                        append("Authorization", "Bearer $token")
                    }
                }
            }
        }
    }
    single{ ApiServiceImpl(get()) }
    factory <ApiService> { MyHttpClient.clients().create(ApiService::class.java) }
    viewModel { LoginVm(get()) }
    viewModel { HomeVm(get()) }
}

fun getAccessToken(): String {
    val token = MyPreference.getValue(TOKEN)
    return token
}