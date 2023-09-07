package com.childmathematics.android.shiftschedule


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import com.childmathematics.android.basement.lib.ads.yandex.InitBannerView
import com.childmathematics.android.basement.lib.ads.yandex.mBannerAdEventListener
import com.childmathematics.android.basement.lib.ads.yandex.mYaInterstitialAd
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoViewModel
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.childmathematics.android.basement.lib.ads.admob.addInterstitialCallbacks
import com.childmathematics.android.basement.lib.ads.admob.loadInterstitial
import com.childmathematics.android.shiftschedule.navigation.NavigateScreen1
import com.childmathematics.android.shiftschedule.BuildConfig

//lateinit var binding: ActivityFlexBannerAdBinding
lateinit var appContext: Context

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    val todoViewModel by viewModels<TodoViewModel>()
    private val YANDEX_MOBILE_ADS_TAG = "Yandex Mobile Ads"
    private val YANDEX_METRIC_TAG = "Yandex Metric"
    val MainActivity_TAG = "MainActivity"
    var ya_banner_id:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

 //==============================================================
        setContent {
            appContext = LocalContext.current
            // меню навигации
            NavigateScreen1(todoViewModel)

            if (BuildConfig.YaAdsEnable) {
                if (BuildConfig.DEBUG) {
                    ya_banner_id=getString(R.string.ya_ads_demo_banner_id)
                }
                else ya_banner_id=getString(R.string.ya_ads_banner_id)

                InitBannerView(mBannerAdEventListener, ya_banner_id)
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
        //==================================================================
        override fun onDestroy() {
            if (BuildConfig.YaAdsEnable) {
//                mYaInterstitialAd!!.destroy()
                mYaInterstitialAd?.setAdEventListener(null)
                mYaInterstitialAd = null
                Log.d(YANDEX_MOBILE_ADS_TAG, MainActivity_TAG + " Interstutial onDestroy")
            }
            super.onDestroy()
        }


}

