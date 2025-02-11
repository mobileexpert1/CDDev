package com.composetut

import android.app.Application
import com.composetut.data.MyPreference
import com.composetut.di.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        MyPreference.init(this)
       startKoin {
           androidContext(this@MyApp)
           modules(myModule())
       }

    }

}