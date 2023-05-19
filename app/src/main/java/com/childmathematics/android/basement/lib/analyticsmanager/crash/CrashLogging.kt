package com.childmathematics.android.basement.lib.analyticsmanager.crash

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.childmathematics.android.basement.core.loggr.Logging

class CrashLogging : Logging {
    override fun log(priority: Int, tag: String, message: String, throwable: Throwable?) {
        if (priority == Log.ASSERT) {
            Firebase.crashlytics.log(message)
            if (throwable != null) {
                Firebase.crashlytics.recordException(throwable)
            }
        }
    }
}
