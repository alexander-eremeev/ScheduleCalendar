/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2018 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */
package com.childmathematics.android.shiftschedule.shiftads

import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.interstitial.InterstitialAd

var YaAdsEnable = true     // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt

var mAdRequest: AdRequest? = null
var mYaInterstitialAd: InterstitialAd? = null


// load the interstitial ad
fun loadYaInterstitial() {
    mYaInterstitialAd!!.loadAd(mAdRequest!!)
}

// show the interstitial ad
fun showYaInterstitial() {

        loadYaInterstitial()
        AdsInterstutialTimer(180000L)  //реклама через 180 cсек

}
/*
fun YaAdsInterstutialTimer(interstutialDelay: Long){
    Timer().schedule(interstutialDelay) {
        mInterstitialAdOnOff = true
        Log.d("YaAdsInterstutial", "YaAdsIntTimer ON1.")
    }
}
*/