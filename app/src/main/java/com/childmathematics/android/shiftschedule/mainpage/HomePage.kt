package com.childmathematics.android.shiftschedule.mainpage

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.composecalendar.header.NameMonthRus
import com.childmathematics.android.shiftschedule.getShift500
import com.childmathematics.android.shiftschedule.navigation.ui.ComposeTutorialsTheme
import com.childmathematics.android.shiftschedule.shiftads.*
import java.time.LocalDate

/*
class HomePage {
}

 */
@Composable
fun HomePageShow() {
    var changeDp: Dp

    if(BuildConfig.AdMobEnable || BuildConfig.YaAdsEnable) {
        changeDp = 50.dp
    } else changeDp = 0.dp

//    Surface(elevation = 8.dp, shape = RoundedCornerShape(12.dp)) {
//    Box(contentAlignment = Alignment.BottomStart, modifier = Modifier
    Box(contentAlignment = Alignment.TopStart, modifier = Modifier
        .background(Color.LightGray)
        .padding(0.dp, changeDp, 0.dp, 0.dp)
    ) {
        Image(
            contentScale = ContentScale.Crop,
//            modifier = Modifier.height(160.dp),
            modifier = Modifier.fillMaxHeight(),
            painter = painterResource(id = R.drawable.carpenterworks_1280),
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
//                .padding(0.dp, changeDp, 0.dp, 0.dp)
//          .width(400.dp)

//                .wrapContentHeight()
//                .background(Color.White)
               .padding(0.dp)
        ) {

            Text(
                text =String.format("%4d",LocalDate.now().year),
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
//          modifier = Modifier.padding(8.dp)
                modifier = Modifier
                    .align(CenterHorizontally),
            )
            Text(
                text =NameMonthRus(LocalDate.now().monthValue),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
//          modifier = Modifier.padding(8.dp)
                modifier = Modifier
                    .align(CenterHorizontally),
            )
            //NameMonthRus(monthNumber: Int)
            Text(
                text = String.format("%2d",LocalDate.now().dayOfMonth),
                fontWeight = FontWeight.Bold,
                fontSize = 200.sp,
//          modifier = Modifier.padding(8.dp)
                modifier = Modifier
                    .align(CenterHorizontally),
            )

            Text(
                text = getDayMonthTodayText (),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
//          modifier = Modifier.padding(8.dp)
                modifier = Modifier
                    .align(CenterHorizontally),
            )


        }
  }

}
fun getDayMonthTodayText ():String
{
    val day: Int =LocalDate.now().dayOfWeek.value

    when (day) {
        1 -> return "понедельник"
        2 -> return "вторник"
        3 -> return "среда"
        4 -> return "четверг"
        5 -> return "пятница"
        6 -> return "суббота"
        7 -> return "воскресенье"
    }
    if (day > 7 || day<1) {return "Error"}
    return "Error"
}
