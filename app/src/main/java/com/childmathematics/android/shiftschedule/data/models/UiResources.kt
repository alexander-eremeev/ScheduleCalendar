package com.childmathematics.android.shiftschedule.data.models

sealed class UiResources<out T> {
    data class Success<out T>(val data: T) : UiResources<T>()
    data class Error( val message: String ) : UiResources < Nothing >()
    object Loading : UiResources < Nothing >()
}
