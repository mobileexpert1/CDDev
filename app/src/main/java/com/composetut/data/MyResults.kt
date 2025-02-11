package com.composetut.data

sealed class MyResults<T>(var data:T?=null, var error:String="") {
    class IsLoading<T>():MyResults<T>()
    class IsSuccess<T>(mData:T?=null):MyResults<T>(data = mData)
    class IsError<T>(mError: String):MyResults<T>(error=mError)
}