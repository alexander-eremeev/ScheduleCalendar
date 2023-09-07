package com.childmathematics.android.shiftschedule

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import com.childmathematics.android.basement.lib.ads.admob.addInterstitialCallbacks
import com.childmathematics.android.basement.lib.ads.admob.loadInterstitial
import com.childmathematics.android.basement.lib.ads.yandex.InitBannerView
import com.childmathematics.android.basement.lib.ads.yandex.mBannerAdEventListener
import com.childmathematics.android.basement.lib.ads.yandex.mYaInterstitialAd
import com.childmathematics.android.shiftschedule.navigation.NavigateScreen1
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoViewModel
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.ExperimentalCoroutinesApi

// lateinit var binding: ActivityFlexBannerAdBinding
lateinit var appContext: Context

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    val todoViewModel by viewModels<TodoViewModel>()
    private val yandexMobileAdsTag = "Yandex Mobile Ads"
    private val yandexMetricTag = "Yandex Metric"
    val mainActivityTag = "MainActivity"
    var yaBannerId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ==============================================================
        setContent {
            appContext = LocalContext.current
            // меню навигации
            NavigateScreen1(todoViewModel)

            if (BuildConfig.YaAdsEnable) {
                if (BuildConfig.DEBUG) {
                    yaBannerId = getString(R.string.ya_ads_demo_banner_id)
                } else {
                    yaBannerId = getString(R.string.ya_ads_banner_id)
                }

                InitBannerView(mBannerAdEventListener, yaBannerId)
            }
        }
        if (BuildConfig.AdMobEnable) {
            // initialize the Mobile Ads SDK
            MobileAds.initialize(this) { }

            // load the interstitial ad
            loadInterstitial(this)

            // add the interstitial ad callbacks
            addInterstitialCallbacks(this)
        }
        if (BuildConfig.YaAdsEnable) {
        }
    }

    // ==================================================================
    override fun onDestroy() {
        if (BuildConfig.YaAdsEnable) {
//                mYaInterstitialAd!!.destroy()
            mYaInterstitialAd?.setAdEventListener(null)
            mYaInterstitialAd = null
            Log.d(yandexMobileAdsTag, mainActivityTag + " Interstutial onDestroy")
        }
        super.onDestroy()
    }
}
