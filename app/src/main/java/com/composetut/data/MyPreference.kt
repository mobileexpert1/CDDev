package com.composetut.data

import android.content.Context
import android.content.SharedPreferences

object MyPreference {

    private lateinit var preference:SharedPreferences

    fun init(context: Context){
        preference=context.getSharedPreferences("MyPref",Context.MODE_PRIVATE)
    }

    fun getValue(key: String):String{
        return if (::preference.isInitialized)
            preference.getString(key,"").toString() else ""
    }

    fun saveValue(key:String,value:String){
        if (::preference.isInitialized){
            preference.edit().putString(key, value).apply()
        }
    }

    fun clearValue(key: String){
        if (::preference.isInitialized){
            preference.edit().remove(key).apply()
        }
    }

    fun clear(){
        if (::preference.isInitialized){
            preference.edit().clear().apply {  }
        }
    }
}