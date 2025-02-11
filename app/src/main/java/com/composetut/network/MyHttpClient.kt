package com.composetut.network

import com.composetut.data.MyPreference
import com.composetut.data.PreferenceKey
import com.composetut.data.ServerUtils
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object MyHttpClient {
    var baseurl= ServerUtils.AUTH_BASE_URL
    fun clients(): Retrofit {

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        val okHttpClient = OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)

            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)

        okHttpClient.addInterceptor { chain ->
            val originn = chain.request()
            val newRequest = originn.newBuilder()
                .addHeader("accept", "text/plain")
                .addHeader("Content-Type", "application/json")
                .apply {
                    if (getToken().isNotEmpty()) {
                        addHeader("Authorization", "Bearer ${getToken()}")
                    }
                }
                .method(originn.method, originn.body)

            chain.proceed(newRequest.build())
        }


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(interceptor)
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(baseurl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
            .build()
    }

    fun getToken(): String {
        baseurl = if (MyPreference.getValue(PreferenceKey.TOKEN).isNotEmpty()){
            ServerUtils.BASE_URL
        }else{
            ServerUtils.AUTH_BASE_URL
        }
        return MyPreference.getValue(PreferenceKey.TOKEN)
    }
    
}