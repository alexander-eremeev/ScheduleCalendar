package com.childmathematics.android.shiftschedule.webview

import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.childmathematics.android.basement.lib.ads.yandex.mYaInterstitialAdOnOff
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.util.bannerHightMin
import com.childmathematics.android.shiftschedule.util.bannerHightPlus
import com.childmathematics.android.shiftschedule.util.bannerHightWithVideoMin

@Composable
fun WebViewMainScreen(siteName:  String) {

//        val siteName = rememberSaveable { mutableStateOf("")}
//'        val siteName1 = rememberSaveable { mutableStateOf("")}
//        var isSearching by remember { mutableStateOf(false) }
//------------------------
    var changeHightDp: Int
    var screenHeightDp: Int

    screenHeightDp = LocalConfiguration.current.screenHeightDp
    changeHightDp = screenHeightDp
    if( BuildConfig.YaAdsEnable) {
        mYaInterstitialAdOnOff = false      // запрет YaInterstitial  рекламы
        if (screenHeightDp > 800)
            changeHightDp = screenHeightDp- bannerHightWithVideoMin - bannerHightPlus
        else changeHightDp = screenHeightDp- bannerHightMin - bannerHightPlus
    }

//----------------------------------
    Box(modifier = Modifier
        .padding(0.dp,0.dp,0.dp,0.dp)          // для баннера
//        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .size(
            (LocalConfiguration.current.screenWidthDp).dp,
            changeHightDp.dp
        )
        .verticalScroll(rememberScrollState(changeHightDp))


    ) {
        Surface(
            modifier = Modifier
//                .fillMaxSize()
                .background(MaterialTheme.colors.surface)

        )
        {
            WebViewScreen(siteName = siteName)
//                    WebViewScreen(siteName = siteName)
        }
    }
//    WebViewScreen(siteName = siteName)
}

    @Composable
    fun WebViewScreen(siteName: String) {

        AndroidView(factory = { context->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
//                loadUrl("http://www.${siteName}.com")
                loadUrl("${siteName}")
//                loadUrl("file:///android_asset/about.html")
            }
        }, update = { webView->
            val webSettings: WebSettings = webView.getSettings()
            webSettings.javaScriptEnabled = true
            webView.loadUrl("${siteName}")
        })
    }

