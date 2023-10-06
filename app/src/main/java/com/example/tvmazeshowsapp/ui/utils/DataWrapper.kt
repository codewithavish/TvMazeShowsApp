package com.example.tvmazeshowsapp.ui.utils


sealed class DataWrapper<T>(val data :T? = null, val errorMessage: String? = null) {

    class Loading<T> : DataWrapper<T>()
    class Success<T>(data:T? = null): DataWrapper<T>(data=data)
    class Error<T>(errorMessage: String?):DataWrapper<T>(errorMessage = errorMessage)

}