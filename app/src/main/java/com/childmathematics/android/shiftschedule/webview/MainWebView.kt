package com.childmathematics.android.shiftschedule.webview

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.webview.presentation.components.CustomButton
import com.childmathematics.android.shiftschedule.webview.presentation.components.CustomTextField
import com.childmathematics.android.shiftschedule.webview.ui.theme.WebViewTheme

@Composable
fun WebViewMainScreen(siteName:  String) {

//        val siteName = rememberSaveable { mutableStateOf("")}
//'        val siteName1 = rememberSaveable { mutableStateOf("")}
//        var isSearching by remember { mutableStateOf(false) }
    var changeDp: Dp

    if(BuildConfig.AdMobEnable|| BuildConfig.YaAdsEnable) {
        changeDp = 50.dp
    } else changeDp = 0.dp

    Box(modifier = Modifier
        .padding(0.dp,changeDp,0.dp,0.dp)          // для баннера
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
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
//            webView.loadUrl("http://www.${siteName}.com")
//            webView.loadUrl("file:///android_asset/${siteName}.html")
//--------------+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            val webSettings: WebSettings = webView.getSettings()
            webSettings.javaScriptEnabled = true

//----------------------------------------------------------

            webView.loadUrl("${siteName}")
        })
    }

