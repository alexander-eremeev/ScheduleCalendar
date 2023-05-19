package com.childmathematics.android.basement.testdebug_no_op

import android.content.Context
import com.childmathematics.android.basement.core.loggr.Logging
import okhttp3.Interceptor

object DebugTools {

    fun init() {

    }

    fun getLoggings(): List<Logging> {
        return listOf()
    }

    fun getInterceptors(): List<Interceptor> {
//        fun getInterceptors(context: Context): List<Interceptor> {
        return listOf()
    }

}
