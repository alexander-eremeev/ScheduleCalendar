/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2018 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */
package com.childmathematics.android.shiftschedule.shiftads

//import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.util.durationAds
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.interstitial.InterstitialAd
import java.util.*
import kotlin.concurrent.schedule


//var YaAdsEnable = true     // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt

var mAdYaRequest: AdRequest? = null
//var mAdYaBannerRequest: AdRequest? = null
var mYaInterstitialAd: InterstitialAd? = null
var mBannerAdView: BannerAdView? = null
var mYaInterstitialAdOnOff = false


// load the interstitial ad
fun loadYaInterstitial() {
    mYaInterstitialAd!!.loadAd(mAdYaRequest!!)
}

// show the interstitial ad
fun showYaInterstitial() {

        loadYaInterstitial()
        YaAdsInterstutialTimer(durationAds)  //реклама через 180 cсек

}

fun YaAdsInterstutialTimer(interstutialDelay: Long){
    Timer().schedule(interstutialDelay) {
        mYaInterstitialAdOnOff = true
           if (BuildConfig.DEBUG) {
               Log.d("YaAdsInterstutial", "YaAdsIntTimer ON1.")
        }
 }
}


//-------------------------------
//================================
@Composable
fun InitBannerView(mBannerAdEventListener:BannerAdEventListener) {
    /*
    * Replace demo R-M-DEMO-320x50 with actual Block ID
    * Following demo Block IDs may be used for testing:
    * R-M-DEMO-300x250 for AdSize.BANNER_300x250
    * R-M-DEMO-300x250-context for AdSize.BANNER_300x250
    * R-M-DEMO-300x300-context for AdSize.BANNER_300x300
    * R-M-DEMO-320x50 for AdSize.BANNER_320x50
    * R-M-DEMO-320x50-app_install for AdSize.BANNER_320x50
    * R-M-DEMO-320x100-context for AdSize.BANNER_320x100
    * R-M-DEMO-728x90 for AdSize.BANNER_728x90
    */
    /* ????????????????????????????????????????
    mBannerAdView!!.setBlockId("R-M-DEMO-320x50")
    mBannerAdView!!.setAdSize(AdSize.BANNER_320x50)

     */
//---------------------------------------------------------------
    if (BuildConfig.DEBUG) {
        Log.d("YaAdsBanner", "INIT0")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        //---------------------------------------------------------------
        if (BuildConfig.DEBUG) {
            Log.d("YaAdsBanner", "INIT1")
        }

        AndroidView(
            factory = { context ->
                BannerAdView(context).apply {
                    setBlockId("R-M-DEMO-320x50")
                    setAdSize(AdSize.BANNER_320x50)
                    loadAd(AdRequest.Builder().build())
                    setBannerAdEventListener(mBannerAdEventListener)
                    visibility = View.VISIBLE

                }
            }

        )
    }
}
val mBannerAdEventListener: BannerAdEventListener = object : BannerAdEventListener {
    override fun onAdLoaded() {
        if (BuildConfig.DEBUG) {
            Log.d("mBannerAdEventList", "onAdLoaded")
        }
        //      mBannerAdView!!.visibility = View.VISIBLE
    }

    override fun onAdFailedToLoad(adRequestError: AdRequestError) {
//            Toast.makeText(this@MainActivity, adRequestError.description, Toast.LENGTH_SHORT).show()
        if (BuildConfig.DEBUG) {
            Log.d("onAdFailedToLoad", adRequestError.description)
        }
    }

    override fun onLeftApplication() {
        if (BuildConfig.DEBUG) {
            Log.d("YaAdsBanner", "onLeftApplication")
        }
    }

    override fun onReturnedToApplication() {
//          Toast.makeText(this@MainActivity, "onReturnedToApplication", Toast.LENGTH_SHORT).show()
        if (BuildConfig.DEBUG) {
            Log.d("YaAdsBanner", "onReturnedToApplication")
        }
    }
}



