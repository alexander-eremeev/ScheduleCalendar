package com.childmathematics.android.shiftschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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

import com.google.android.gms.ads.MobileAds
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

//            MainScreen(todoViewModel)
//            AdBannerNetworkApp()
            NavigateScreen1(todoViewModel,AdMobEnable)
        }
        if (AdMobEnable) {
            // initialize the Mobile Ads SDK
            MobileAds.initialize(this) { }

            // load the interstitial ad
            loadInterstitial(this)

            // add the interstitial ad callbacks
//        addInterstitialCallbacks()
            addInterstitialCallbacks(this)
            // initialize the Mobile Ads SDK
 //           MobileAds.initialize(this) { }

            // load the interstitial ad
 //           loadInterstitial(this)

            // add the interstitial ad callbacks
//        addInterstitialCallbacks()
 //           addInterstitialCallbacks(this)

        }
        if (YaAdsEnable) {
            // initialize the Mobile Ads SDK
            initInterstitialAd()
            Log.d("YaAdsInterstutial", "INIT0")

            // load the interstitial ad
           loadYaInterstitial()

            // add the interstitial ad callbacks
//        addInterstitialCallbacks()
//            addInterstitialCallbacks(this)

            // add the interstitial ad callbacks
//        addInterstitialCallbacks()
//            addInterstitialCallbacks(this)

        }

    }
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
        mAdRequest = AdRequest.Builder().build()
        mYaInterstitialAd!!.setInterstitialAdEventListener(mInterstitialAdEventListener)
        Log.d("YaAdsInterstutial", "INIT")
    }

    override fun onDestroy() {
        if (YaAdsEnable) { mYaInterstitialAd!!.destroy()}
        super.onDestroy()
    }

    private val mInterstitialAdEventListener: InterstitialAdEventListener =
        object : InterstitialAdEventListener {
            override fun onAdLoaded() {
                if (YaAdsEnable) {
                    if (mInterstitialAdOnOff) {
                        mYaInterstitialAd!!.show()
                        mInterstitialAdOnOff = false
                    }
                }
            }

            override fun onAdFailedToLoad(adRequestError: AdRequestError) {
                if (YaAdsEnable) {
                    Log.d("YaAdsInterstutial", adRequestError.toString())
                    mYaInterstitialAd = null
                }
            }

            override fun onAdShown() {
                if (YaAdsEnable) {
                    Log.d("YaAdsInterstutial", "onAdShown")
                    mYaInterstitialAd!!.loadAd(mAdRequest!!)
                }
            }

            override fun onAdDismissed() {
                if (YaAdsEnable) {
                    Log.d("YaAdsInterstutial", "onAdDismissed")
                }
            }

            override fun onLeftApplication() {
                if (YaAdsEnable) {
                    Log.d("YaAdsInterstutial", "onLeftApplication")
                }
            }

            override fun onReturnedToApplication() {
                if (YaAdsEnable) {
                    Log.d("YaAdsInterstutial", "onReturnedToApplication")
                }
            }
        }
}
