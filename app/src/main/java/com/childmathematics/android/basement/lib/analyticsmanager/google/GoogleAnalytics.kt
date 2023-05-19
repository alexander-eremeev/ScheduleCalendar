package com.childmathematics.android.basement.lib.analyticsmanager.google

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.childmathematics.android.basement.core.analytics.AnalyticsApi
import javax.inject.Inject

class GoogleAnalytics @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
) : AnalyticsApi {
    override fun setUser(properties: Map<String, String>) {
    }

    override fun updateUser(properties: Map<String, String>) {
    }

    override fun trackEvent(name: String) {
        firebaseAnalytics.logEvent(name, Bundle())
    }

    override fun trackEvent(name: String, properties: Map<String, String>) {
        val bundle = Bundle()
        properties.forEach {
            bundle.putString(it.key, it.value)
        }
        firebaseAnalytics.logEvent(name, bundle)
    }

    override fun flush() {
    }
}
