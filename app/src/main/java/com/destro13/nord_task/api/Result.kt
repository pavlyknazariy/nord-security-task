package com.destro13.nord_task.api

const val UNKNOWN_ERROR = "Unknown error"
const val NO_SUCH_DATA = "No data in database"

sealed class Result<out T : Any>

class Success<out T : Any>(val data: T) : Result<T>()

class Error(
    val exception: Throwable,
    val message: String = exception.message ?: UNKNOWN_ERROR
) : Result<Nothing>()

class Progress(val isLoading: Boolean) : Result<Nothing>()