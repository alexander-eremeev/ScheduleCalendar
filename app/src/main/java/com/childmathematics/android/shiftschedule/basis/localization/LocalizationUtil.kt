package com.childmathematics.android.shiftschedule.basis.localization

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import java.util.*

object LocalizationUtil {

    @RequiresApi(Build.VERSION_CODES.N)
    fun applyLanguageContext(context: Context, locale: Locale?): Context {
        if (locale == null) return context
        if (locale == getLocale(context.resources.configuration)) return context

        return try {
            setupLocale(locale)
            val resources = context.resources
            val configuration = getOverridingConfig(locale, resources)

//            resources.updateConfiguration(configuration, resources.displayMetrics)
//            resources.updateConfiguration(configuration, resources.displayMetrics)
            context.createConfigurationContext(configuration)
        } catch (exception: Exception) {
            context
        }
    }

    private fun setupLocale(locale: Locale) {
        Locale.setDefault(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList.setDefault(LocaleList(locale))
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getOverridingConfig(locale: Locale, resources: Resources): Configuration {
        val configuration = resources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocales(LocaleList(locale))
        } else {
//            configuration.locale = locale
            configuration.locales.get(0)
//            configuration.locale = locale
        }
        return configuration
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLocale(configuration: Configuration): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.locales.get(0)
        } else {
//            configuration.locale
            configuration.locales.get(0)
        }
    }

}
