package com.childmathematics.android.shiftschedule

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            MainScreen()

        }
    }
    @Composable
    fun MainScreen() {
        StaticCalendar()
    }
}

 */
import com.childmathematics.android.shiftschedule.navigation.NavigateScreen1
import com.childmathematics.android.shiftschedule.shiftads.*
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoScreen
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoViewModel
import com.google.android.gms.ads.AdView

import com.google.android.gms.ads.MobileAds
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    val todoViewModel by viewModels<TodoViewModel>()


//    private var mAdRequest: AdRequest? = null
//    private var mYaInterstitialAd: InterstitialAd? = null
//    private var mLoadInterstitialAdButton: Button? = null

//    @ExperimentalCoroutinesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // меню навигации
            NavigateScreen1(todoViewModel, AdMobEnable)

        }
        if (AdMobEnable) {
            // initialize the Mobile Ads SDK
            MobileAds.initialize(this) { }

            // load the interstitial ad
            loadInterstitial(this)

            // add the interstitial ad callbacks
            addInterstitialCallbacks(this)
         }
        if (YaAdsEnable) {
            initInterstitialAd()
            // load the interstitial ad
            loadYaInterstitial()
            //==============================
            //    mBannerAdView = findViewById<View>(R.id.banner_view) as BannerAdView
//          InitBannerView()
        }
    }

//==================================================================
    fun initInterstitialAd() {
        mYaInterstitialAd = InterstitialAd(this)

        /*
        * Replace demo R-M-DEMO-320x480 with actual Block ID
        * Following demo Block IDs may be used for testing:
        * R-M-DEMO-320x480
        * R-M-DEMO-480x320
        * R-M-DEMO-400x240-context
        * R-M-DEMO-240x400-context
        * R-M-DEMO-video-interstitial
        */
        //       mInterstitialAd.setBlockId("R-M-DEMO-320x480");
        mYaInterstitialAd!!.setBlockId("R-M-DEMO-320x480")
        mAdYaRequest = AdRequest.Builder().build()
        mYaInterstitialAd!!.setInterstitialAdEventListener(mInterstitialAdEventListener)
        if (BuildConfig.DEBUG) {
            Log.d("YaAdsInterstutial", "INIT")
        }
    }

    override fun onDestroy() {
        if (YaAdsEnable) {
            mYaInterstitialAd!!.destroy()
        }
        super.onDestroy()
    }

    private val mInterstitialAdEventListener: InterstitialAdEventListener =
        object : InterstitialAdEventListener {
            override fun onAdLoaded() {
                if (YaAdsEnable) {
                    if (mYaInterstitialAdOnOff) {
                        mYaInterstitialAd!!.show()
                        mYaInterstitialAdOnOff = false
                        Toast.makeText(applicationContext, "Реклама продлится недолго", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            override fun onAdFailedToLoad(adRequestError: AdRequestError) {
                if (YaAdsEnable) {
                    if (BuildConfig.DEBUG) {
                        Log.d("YaAdsInterstutial", adRequestError.toString())
                    }
                    mYaInterstitialAd = null
                }
            }

            override fun onAdShown() {
                if (YaAdsEnable) {
                    if (BuildConfig.DEBUG) {
                        Log.d("YaAdsInterstutial", "onAdShown")
                    }
                    mYaInterstitialAd!!.loadAd(mAdYaRequest!!)
                }
            }

            override fun onAdDismissed() {
                if (YaAdsEnable) {
                    if (BuildConfig.DEBUG) {
                        Log.d("YaAdsInterstutial", "onAdDismissed")
                    }
                }
            }

            override fun onLeftApplication() {
                if (YaAdsEnable) {
                    if (BuildConfig.DEBUG) {
                        Log.d("YaAdsInterstutial", "onLeftApplication")
                    }
                }
            }

            override fun onReturnedToApplication() {
                if (YaAdsEnable) {
                    if (BuildConfig.DEBUG) {
                        Log.d("YaAdsInterstutial", "onReturnedToApplication")
                    }
                }
            }
        }


}

