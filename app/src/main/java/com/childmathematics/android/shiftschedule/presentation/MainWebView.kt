package com.childmathematics.android.shiftschedule.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
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
//+++++++++++++++++++new
    var progress by rememberSaveable { mutableIntStateOf(0) }
    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    /*
    val pullRefreshState = rememberPullRefreshState(isRefreshing, {
        isRefreshing = true
        }
     */

//----------------------------------
    Box(modifier = Modifier
//        .padding(0.dp,0.dp,0.dp,0.dp)          // для баннера
//        .fillMaxSize()
//        .background(MaterialTheme.colors.background)
//            .pullRefresh(pullRefreshState)
//        .verticalScroll(rememberScrollState())
        .size(
            (LocalConfiguration.current.screenWidthDp).dp,
            changeHightDp.dp
        )
        .verticalScroll(rememberScrollState(changeHightDp))


    ) {
        Surface(
            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colors.surface)
        )
        {
            // ------OLD Realese
             //WebViewScreen(siteName = siteName)
            //-----------------------------new  WebView
            WebViewer(isRefreshing = isRefreshing,
                setRefreshed = { isRefreshing = false },
                updateProgress = { currentProgress -> progress = currentProgress },
                WEBSITE=siteName)
            ProgressIndicator(progress)
            //-----------------------------
        }
    }
}
    @Composable
    fun WebViewScreen(siteName: String) {

        AndroidView(factory = { context->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
//                            ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
//                loadUrl("http://www.${siteName}.com")
                loadUrl("${siteName}")
            }
        }, update = { webView->
            val webSettings: WebSettings = webView.getSettings()
            webSettings.javaScriptEnabled = true
            webView.loadUrl("${siteName}")
        })
    }
/*
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainScreen() {
    var progress by rememberSaveable { mutableIntStateOf(0) }
    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(isRefreshing, {
        isRefreshing = true
    })

    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .verticalScroll(rememberScrollState())
    ) {
        WebViewer(isRefreshing = isRefreshing,
            setRefreshed = { isRefreshing = false },
            updateProgress = { currentProgress -> progress = currentProgress })
        ProgressIndicator(progress)
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.background
        )
    }
}
*/
@Composable
private fun ProgressIndicator(progress: Int) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth(), visible = progress in 1..99
    ) {
        LinearProgressIndicator(progress = { progress.toFloat() / 100 })
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun WebViewer(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    setRefreshed: () -> Unit,
    updateProgress: (Int) -> Unit,
    WEBSITE : String
) {
    var webView: WebView? = null
    var isBackEnabled by rememberSaveable { mutableStateOf(false) }
//    var isBackEnabled by rememberSaveable { mutableStateOf(false) }

    // Override back navigation to load WebView's previous webpage
    BackHandler(enabled = isBackEnabled) {
        webView?.goBack()
    }

    AndroidView(modifier = modifier.fillMaxSize(), factory = { context ->
        WebView(context).apply {

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
            )

            webViewClient = object : WebViewClient() {
/*
                // Open external links in web browser
                override fun shouldOverrideUrlLoading(
                    view: WebView?, request: WebResourceRequest?
                ): Boolean {
                    if (request?.url.toString().contains(WEBSITE)) {
                        return false
                    }
                    Intent(Intent.ACTION_VIEW, request?.url).apply {
                        startActivity(context, this, null)
                    }
                    return true
                }

 */

                // Enable BackHandler if WebView can go back
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    isBackEnabled = view?.canGoBack() == true
                }

            }

            webChromeClient = object : WebChromeClient() {

                // Pass up current loading progress to be used by ProgressIndicator function
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    updateProgress(newProgress)
                }

            }

            // Configure WebView client
            with(this.settings) {
                domStorageEnabled = true
                javaScriptEnabled = true
                setSupportZoom(false)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    isAlgorithmicDarkeningAllowed = true
                }
            }
            this.loadUrl(WEBSITE)
            webView = this
        }
    }, update = {
        if (isRefreshing) {
            it.reload()
            setRefreshed()
        }
        webView = it
    })
}

