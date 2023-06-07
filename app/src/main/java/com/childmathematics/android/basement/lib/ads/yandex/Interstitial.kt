/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2018 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */
package com.childmathematics.android.basement.lib.ads.yandex

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.util.durationAds
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import java.util.*
import kotlin.concurrent.schedule

//var YaAdsEnable = true     // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
//var YANDEX_MOBILE_ADS_TAG = "Yandex Mobile Ads"

var mAdYaIntRequest: AdRequest? = null
var mYaInterstitialAd: InterstitialAd? = null
var mYaInterstitialAdOnOff = true
var evMotion: Boolean = false
var t1: String = "T1"
var t2: String = "T2"
var countTap: Int=0
lateinit var applicationContext: Context
var ya_interstitial_id:String=""

// load the interstitial ad
@Composable
fun LoadYaInterstitial() {
    if (BuildConfig.YaAdsEnable) {
        Log.d(YANDEX_MOBILE_ADS_TAG, "Interstitial: loadYaInterstitial.")
        mYaInterstitialAd!!.loadAd(mAdYaIntRequest!!)
    }
}


// show the interstitial ad
@Composable
fun ShowYaInterstitial() {
    if (BuildConfig.YaAdsEnable) {
        Log.d(YANDEX_MOBILE_ADS_TAG, "Interstitial: ShowYaInterstitial.")
        mYaInterstitialAdOnOff = true

        if (mYaInterstitialAd == null) {
            if (BuildConfig.DEBUG) {
//            ya_interstitial_id=Resources.getSystem().getString(R.string.ya_ads_demo_interstitial_id)
                ya_interstitial_id = LocalContext.current.getString(R.string.ya_ads_demo_interstitial_id)
            } else ya_interstitial_id = LocalContext.current.getString(R.string.ya_ads_interstitial_id)

            InitInterstitialAd(ya_interstitial_id)

            LoadYaInterstitial()
        }

        if (!evMotion) yaAdsInterstutialTimerOn(durationAds)  //реклама через durationAds mcек

    }
}

fun yaAdsInterstutialTimerOn(interstutialDelay: Long) {
    if (BuildConfig.YaAdsEnable) {

        Timer(t1).schedule(interstutialDelay) {
            Log.d(YANDEX_MOBILE_ADS_TAG, "Interstitial: yaAdsInterstutialTimerOn ON.")
            // interstutial ON              загружена                  не было нажатий экрана после показа
            if (mYaInterstitialAdOnOff && mYaInterstitialAd!!.isLoaded && !evMotion) {

                mYaInterstitialAd!!.show()
                evMotion = true
            }

            Timer(t1).cancel()
            Timer(t1).purge()

        }
    }
}
fun yaAdsInterstutialTimerOff() {
    if (BuildConfig.YaAdsEnable) {
        if (evMotion) {
            countTap++
            Timer(t2).cancel()
            Timer(t2).purge()
        }
        if (!evMotion) {
            evMotion = true
        }
        Log.d(YANDEX_MOBILE_ADS_TAG, "Interstitial: yaAdsInterstutialTimerOff On.")
        Timer(t2).schedule(durationAds) {
            Log.d(YANDEX_MOBILE_ADS_TAG, "Interstitial: yaAdsInterstutialTimerOff Off.")
            if (evMotion && countTap == 0) {
                evMotion = false
                yaAdsInterstutialTimerOn(durationAds)  //реклама через 180 cек  durationNoPushTastaturAds
            }
            if (countTap > 0) countTap--
        }
    }
}
//-------------------------------
@Composable
fun InitInterstitialAd(Id: String) {
    if (BuildConfig.YaAdsEnable) {
        /*
        context - Activity context, в котором выполняется view.
        Через него можно получить доступ к текущей теме, ресурсам и т.д.
        В частности, он использует оконный менеджер и тему в этом контексте
        для представления UI.
         */
        applicationContext = LocalContext.current
        mYaInterstitialAd = InterstitialAd(LocalContext.current)

        /*
        * Replace demo R-M-DEMO-320x480 with actual Block ID
        * Following demo Block IDs may be used for testing:
        * R-M-DEMO-320x480
        * R-M-DEMO-480x320
        * R-M-DEMO-400x240-context
        * R-M-DEMO-240x400-context
        * R-M-DEMO-video-interstitial
        * R-M-DEMO-interstitial
        */
            mYaInterstitialAd!!.setAdUnitId(Id)
        mAdYaIntRequest = AdRequest.Builder().build()
        mYaInterstitialAd!!.setInterstitialAdEventListener(mInterstitialAdEventListener)
        Log.d(YANDEX_MOBILE_ADS_TAG,"Interstitial: initInterstitialAd INIT")
    }
}

//=================================================
private val mInterstitialAdEventListener = InterstitialAdYandexAdsEventListener()
private class InterstitialAdYandexAdsEventListener : InterstitialAdEventListener {

    override fun onAdLoaded() {
        if (BuildConfig.YaAdsEnable) {
            Log.d(YANDEX_MOBILE_ADS_TAG,"Interstitial: onAdLoaded ")
        }
    }

    override fun onAdFailedToLoad(adRequestError: AdRequestError) {
        if (BuildConfig.YaAdsEnable) {
            Log.d(YANDEX_MOBILE_ADS_TAG, "Interstitial: onAdFailedToLoad:$adRequestError")
            mYaInterstitialAd = null
        //    mYaInterstitialAdOnOff = false
        }
    }

    override fun onAdShown() {
        if (BuildConfig.YaAdsEnable) {
            Log.d(YANDEX_MOBILE_ADS_TAG,"Interstitial: onAdShown")

            mYaInterstitialAd!!.loadAd(mAdYaIntRequest!!)
            mYaInterstitialAdOnOff = false  // отмена показа до нажатия основного меню
        }
    }

    override fun onAdDismissed() {
        if (BuildConfig.YaAdsEnable) {
            Log.d(YANDEX_MOBILE_ADS_TAG,"Interstitial: onAdDismissed")
            evMotion = false            //разрешение Interstitial
        }
    }

    override fun onAdClicked() {
//        TODO("Not yet implemented")
        if (BuildConfig.YaAdsEnable) {
            Log.d(YANDEX_MOBILE_ADS_TAG, "Interstitial: onAdClicked")
        }
    }



    override fun onLeftApplication() {
        if (BuildConfig.YaAdsEnable) {
                Log.d(YANDEX_MOBILE_ADS_TAG,"Interstitial: onLeftApplication")
//            mYaInterstitialAdOnOff = false
            mYaInterstitialAd = null
        }
    }

    override fun onReturnedToApplication() {
        if (BuildConfig.YaAdsEnable) {
                Log.d(YANDEX_MOBILE_ADS_TAG,"Interstitial: onReturnedToApplication")
//            mYaInterstitialAdOnOff = false
            mYaInterstitialAd = null
        }
    }
    // Вызывается, когда зарегистрирован показ.
    override fun onImpression(p0: ImpressionData?) {
//    TODO("Not yet implemented")
        if (BuildConfig.YaAdsEnable) {
            Log.d(YANDEX_MOBILE_ADS_TAG, "Interstitial: onImpression-зарегистрирован показ")
        }
    }
}

//===============================================


