package com.childmathematics.android.shiftschedule.data.models

sealed class UiResources<T> {
    data class Success<T>(val data: T) : UiResources<T>()
    data class Error<T>(val message: String, val data: T? = null) : UiResources<T>()
    class Loading<T> : UiResources<T>()
}
