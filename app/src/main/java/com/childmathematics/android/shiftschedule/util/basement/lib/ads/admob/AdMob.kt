package com.childmathematics.android.basement.lib.ads.admob

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import java.util.*
import kotlin.concurrent.schedule

//var AdMobEnable = false  // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
var mInterstitialAd: InterstitialAd? = null
var mInterstitialAdOnOff = false

// load the interstitial ad
fun loadInterstitial(context: Context) {
    InterstitialAd.load(
        context,
        context.getString(R.string.ad_id_interstitial),
//        AdRequest.Builder().build(),
        AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
                if (BuildConfig.DEBUG) {
                    Log.d("loadInterstitial", adError.message)
                }
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                if (BuildConfig.DEBUG) {
                    Log.d("onAdLoaded", "Ad was loaded.")
                }
            }

         }
    )
}

// add the interstitial ad callbacks
//fun addInterstitialCallbacks() {
    fun addInterstitialCallbacks(context: Context) {
    mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            if (BuildConfig.DEBUG) {
                Log.d("addInterstitialCallb", "Ad failed to show.")
            }
        }

        override fun onAdShowedFullScreenContent() {
            mInterstitialAd = null
            if (BuildConfig.DEBUG) {
                Log.d("onAdShowedFull", "Ad showed fullscreen content.")
            }
            loadInterstitial(context)

        }

        override fun onAdDismissedFullScreenContent() {
            if (BuildConfig.DEBUG) {
                Log.d("onAdDismissedContent", "Ad was dismissed.")
            }
        }
    }
}

// show the interstitial ad
fun showInterstitial(context: Context ) {
    val activity = context.findActivity()

//    if (mInterstitialAd != null ) {
        if (mInterstitialAd != null && mInterstitialAdOnOff) {
            Toast.makeText(context, "Реклама продлится недолго", Toast.LENGTH_SHORT).show();
            mInterstitialAd?.show(activity!!)
            mInterstitialAdOnOff = false
            loadInterstitial(context)
    } else {
            if (BuildConfig.DEBUG) {
                Log.d("showInterstitial", "The interstitial ad wasn't ready yet.")
                // Режим отладки, ведём логи
            }
    }
}
fun AdsInterstutialTimer(interstutialDelay: Long){
        Timer().schedule(interstutialDelay) {
            mInterstitialAdOnOff = true
            if (BuildConfig.DEBUG) {
                Log.d("AdsInterstutialTimer", "AdsIntTimer ON1.")
            }
        }


}

