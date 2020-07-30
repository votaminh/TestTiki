package com.msc.tikitest.repository

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val errorMessage: String = "") : UseCaseResult<Nothing>()
}