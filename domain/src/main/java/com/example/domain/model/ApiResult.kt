package com.example.domain.model

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()
    data class Error(val code: Int? = null, val exception: Throwable? = null) :
        ApiResult<Nothing>()

    object Empty : ApiResult<Nothing>()
}