package com.childmathematics.android.basement.testdebug

import android.util.Log
import com.childmathematics.android.basement.core.loggr.Logging

class DebugLogging : Logging {

    override fun log(priority: Int, tag: String, message: String, throwable: Throwable?) {
        when (priority) {
            Log.DEBUG -> Log.d(tag, message)
            Log.VERBOSE -> Log.v(tag, message)
            Log.ERROR -> Log.e(tag, message, throwable)
            Log.WARN -> Log.w(tag, message, throwable)
            Log.INFO -> Log.i(tag, message, throwable)
            Log.ASSERT -> Log.wtf(tag, message, throwable)
        }
    }

}
