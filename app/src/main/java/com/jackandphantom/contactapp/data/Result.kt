package com.jackandphantom.contactapp.data

import java.lang.Exception


sealed class Result <out R> {

    data class Success<out T> (val data : T) : Result<T>()

    data class Error (val exception : Exception) : Result<Nothing>()
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null