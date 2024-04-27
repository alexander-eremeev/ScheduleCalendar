package com.childmathematics.android.shiftschedule.ui.main

//import androidx.compose.foundation.layout.*
//import com.childmathematics.android.basement.lib.composecalendar.header.NameMonthRus
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.childmathematics.android.basement.lib.ads.yandex.mYaInterstitialAdOnOff
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.util.bannerHightMin
import com.childmathematics.android.shiftschedule.util.bannerHightPlus
import com.childmathematics.android.shiftschedule.util.bannerHightWithVideoMin
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPageShow() {
    var changeHightDp: Int
    var screenHeightDp: Int

    screenHeightDp = LocalConfiguration.current.screenHeightDp
    changeHightDp = screenHeightDp
    if( BuildConfig.YaAdsEnable) {
        mYaInterstitialAdOnOff = false      // запрет YaInterstitial  рекламы
        if (screenHeightDp > 800)
            changeHightDp = screenHeightDp-bannerHightWithVideoMin-bannerHightPlus
        else changeHightDp = screenHeightDp-bannerHightMin-bannerHightPlus
    }
    Box(contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .background(Color.LightGray)
                .verticalScroll(rememberScrollState())
                .size((LocalConfiguration.current.screenWidthDp).dp, changeHightDp.dp)
    ) {
        Image(
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                ,
            painter =  painterResource(id = R.drawable.carpenterworks_1280),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
               .padding(0.dp)
        ) {
            Text(
                text =String.format("%4d",LocalDate.now().year),
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                modifier = Modifier
                    .align(End),
            )
            LocalDate.now().month
                .getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())?.let {
                    Text(
                        text = it
                            .lowercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        modifier = Modifier
                            .align(End),
                    )
                }
            Text(
                text = String.format("%2d",LocalDate.now().dayOfMonth),
                fontWeight = FontWeight.Bold,
                fontSize = 200.sp,
                modifier = Modifier
                .align(End),
            )
            LocalDate.now().dayOfWeek
                .getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())?.let {
                    Text(
                        text = it
                            .lowercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        modifier = Modifier
                            .align(End),
                    )
                }
        }
  }
}

