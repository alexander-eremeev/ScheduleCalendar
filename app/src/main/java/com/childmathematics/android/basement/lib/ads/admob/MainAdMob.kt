package com.childmathematics.android.basement.lib.ads.admob

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.childmathematics.android.shiftschedule.*
import com.childmathematics.android.shiftschedule.util.durationAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/*

*/
@Composable
fun AdNetworkApp() {
    val adWidth = LocalConfiguration.current.screenWidthDp - 32
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Adaptive Banner", modifier = Modifier.padding(bottom = 16.dp))

        // shows an adaptive banner test ad
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
//                      adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                        context,
                        adWidth
                    )
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

        Text("Inline Adaptive Banner", modifier = Modifier.padding(16.dp))

        // shows an inline adaptive banner test ad
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(
//                        adSize = AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(
                        context,
                        adWidth
                    )
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

        Text("Regular Banner", modifier = Modifier.padding(16.dp))

        // shows a traditional banner test ad
        AndroidView(
            factory = { context ->
                AdView(context).apply {
//                    adSize = AdSize.BANNER
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

        // shows an interstitial ad on button click (on the first click only)
        Button(
            onClick = {
                loadInterstitial(context)
                showInterstitial(context)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Show Interstitial")
        }

        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello Ad Network!")
            Text("More texts")
        }

        Text("And some more texts")
    }
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AdNetworkApp()
}
*/
@Composable
fun AdBannerNetworkApp() {
    val adWidth = LocalConfiguration.current.screenWidthDp - 32
//    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
//        Text("Adaptive Banner", modifier = Modifier.padding(bottom = 16.dp))

        // shows an adaptive banner test ad
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
//                        adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                        context,
                        adWidth
                    )
                    adUnitId = context.getString(R.string.ad_id_banner)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun AdBannerNetworkAppPreview() {
    AdBannerNetworkApp()
}
//============================================================
//    AdInterstitialNetworkApp()
@Composable
fun AdInterstitialNetworkApp(context: Context) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

//                mInterstitialAdOnOff = true
                showInterstitial(context)

                AdsInterstutialTimer(durationAds)  //реклама через 180 cсек

    }

}
@Preview(showBackground = true)
@Composable
fun AdInterstitialNetworkAppPreview() {
    AdInterstitialNetworkApp(LocalContext.current)
}
