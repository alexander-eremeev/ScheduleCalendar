/*
 * This file is a part of the Yandex Advertising Network
 *
 * Version for Android (C) 2018 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://legal.yandex.com/partner_ch/
 */
package com.childmathematics.android.basement.lib.ads.yandex

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.childmathematics.android.shiftschedule.util.bannerHightMin
import com.childmathematics.android.shiftschedule.util.bannerHightWithVideoMin
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData

//var YaAdsEnable = true     // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
var YANDEX_MOBILE_ADS_TAG = "Yandex Mobile Ads"

//var mAdYaBannerRequest: AdRequest? = null
//private var mBannerAdView: BannerAdView? = null
private var bannerWidth = 0
///================================
@Composable
fun InitBannerView(
    mBannerAdEventListener: BannerAdEventListener,Id: String) {
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
    *     private var BANNER_BLOCK_ID = "R-M-DEMO-320x50" // TEST AD ID

    */
 //---------------------------------------------------------------
//    val adWidth = LocalConfiguration.current.screenWidthDp - 32
//    var adHeight: Int
    var bannerHeight:Int
    var bannerOffsetHightDp:Int
    var screenHeightDp:Int

    screenHeightDp = LocalConfiguration.current.screenHeightDp
    if (screenHeightDp > 800) {
//        bannerOffsetHightDp = screenHeightDp - bannerHightWithVideoMin - bannerHightPlus
        bannerOffsetHightDp = screenHeightDp - bannerHightWithVideoMin
        bannerHeight = bannerHightWithVideoMin
    }
    else {
//        bannerOffsetHightDp = screenHeightDp - bannerHightMin - bannerHightPlus
        bannerOffsetHightDp = screenHeightDp - bannerHightMin
        bannerHeight = bannerHightMin
    }


 //   adHeight = if (bannerHeight > 700) bannerHeight-70 else bannerHeight-50
//    adHeight =  bannerHeight-50

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                    .fillMaxWidth()
                    .absoluteOffset(0.dp,bannerOffsetHightDp.dp)
                    .verticalScroll(rememberScrollState())
    )
    {
        bannerWidth = LocalConfiguration.current.screenWidthDp
//        bannerHeight = LocalConfiguration.current.screenHeightDp
        AndroidView(
            factory = { LocalContext ->
                BannerAdView(LocalContext).apply {
                    setAdUnitId(Id)
//                    setAdUnitId("demo-banner-yandex")
//                      setAdUnitId("demo-banner-vungle")
//                      setAdSize(AdSize.flexibleSize(320, 50))
                    Log.d(YANDEX_MOBILE_ADS_TAG, "Banner: Banner INIT screenHeightDp=$screenHeightDp")
//                    if (bannerHeight > 300) bannerHeight = 50
//                    setAdSize(AdSize.flexibleSize(bannerWidth, bannerHeight))
                    setAdSize(BannerAdSize.inlineSize(LocalContext,bannerWidth, bannerHeight))
                    loadAd(AdRequest.Builder().build())
                   setBannerAdEventListener(mBannerAdEventListener)

               }
            }
        )
    }
}


val mBannerAdEventListener = BannerAdYandexAdsEventListener()
    class BannerAdYandexAdsEventListener : BannerAdEventListener {

    override fun onAdLoaded() {
        Log.d(YANDEX_MOBILE_ADS_TAG, "Banner: mBannerAdEventList onAdLoaded")
    }

    override fun onAdFailedToLoad(adRequestError: AdRequestError) {
             Log.d(YANDEX_MOBILE_ADS_TAG,"Banner: onAdFailedToLoad: "+ adRequestError.description)
    }

    override fun onAdClicked() {
//        TODO("Not yet implemented")
            Log.d(YANDEX_MOBILE_ADS_TAG,"Banner: onAdClicked ")
    }



    override fun onLeftApplication() {
            Log.d(YANDEX_MOBILE_ADS_TAG, "Banner: Banner onLeftApplication")
    }

    override fun onReturnedToApplication() {
//          Toast.makeText(this@MainActivity, "onReturnedToApplication", Toast.LENGTH_SHORT).show()
            Log.d(YANDEX_MOBILE_ADS_TAG, "Banner: Banner onReturnedToApplication")
    }

    override fun onImpression(p0: ImpressionData?) {
//        TODO("Not yet implemented")
        Log.d(YANDEX_MOBILE_ADS_TAG,"Banner: onImpression ")
    }


}
