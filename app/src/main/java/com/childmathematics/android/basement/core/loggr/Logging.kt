package com.childmathematics.android.basement.core.loggr

interface Logging {
    fun log(priority: Int, tag: String, message: String, throwable: Throwable?)
}
