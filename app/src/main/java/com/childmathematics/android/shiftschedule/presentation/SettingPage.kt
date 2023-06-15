package com.childmathematics.android.shiftschedule.mainpage

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.basement.lib.ads.admob.AdBannerNetworkApp
import com.childmathematics.android.basement.lib.ads.admob.AdInterstitialNetworkApp
import com.childmathematics.android.basement.lib.ads.yandex.ShowYaInterstitial
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.basement.lib.ads.yandex.mYaInterstitialAdOnOff
import com.childmathematics.android.shiftschedule.aspects.localized.setting.ui.LanguageScreen
import com.childmathematics.android.shiftschedule.aspects.localized.setting.ui.LocalizedSettingViewModel
import com.childmathematics.android.shiftschedule.basis.uiextension.rememberBottomSheetNavigator
import com.childmathematics.android.shiftschedule.navigation.Routes
import com.childmathematics.android.shiftschedule.runtime.navigation.DefaultMainBottomSheetConfig
import com.childmathematics.android.shiftschedule.runtime.navigation.SettingFlow
import com.childmathematics.android.shiftschedule.runtime.navigation.SettingNavHost
//import com.childmathematics.android.shiftschedule.runtime.navigation.SettingNavHost
import com.childmathematics.android.shiftschedule.util.bannerHightMin
import com.childmathematics.android.shiftschedule.util.bannerHightPlus
import com.childmathematics.android.shiftschedule.util.bannerHightWithVideoMin
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
//import com.yandex.metrica.YandexMetrica


@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
//fun SettingPageShow(context: Context,currentRoute : String) {
fun SettingPageShow(currentRoute : String) {

//=========================
 //   val bottomSheetNavigator = rememberBottomSheetNavigator()
 //   val bottomSheetConfig = remember { mutableStateOf(DefaultMainBottomSheetConfig) }
//    val navController = rememberNavController(bottomSheetNavigator)
//-----------------------------

    var changeHightDp: Int
    var screenHeightDp: Int

    screenHeightDp = LocalConfiguration.current.screenHeightDp
    changeHightDp = screenHeightDp
    if( BuildConfig.YaAdsEnable || BuildConfig.AdMobEnable) {
        mYaInterstitialAdOnOff = false      // запрет YaInterstitial  рекламы
//        ShowYaInterstitial()
        if (screenHeightDp > 800)
            changeHightDp = screenHeightDp-bannerHightWithVideoMin-bannerHightPlus
        else changeHightDp = screenHeightDp-bannerHightMin-bannerHightPlus
    }
    if (BuildConfig.AdMobEnable) {
        AdBannerNetworkApp()
        AdInterstitialNetworkApp(LocalContext.current)
    }

//    Surface(elevation = 8.dp, shape = RoundedCornerShape(12.dp)) {
//    Box(contentAlignment = Alignment.BottomStart, modifier = Modifier
    Box(contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .background(Color.LightGray)
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
//                .size(300.dp, 250.dp)               //(LocalConfiguration.current.screenHeightDp-50).dp
            //               .size((LocalConfiguration.current.screenWidthDp).dp, (LocalConfiguration.current.screenHeightDp-70).dp)
            .size((LocalConfiguration.current.screenWidthDp).dp, changeHightDp.dp)
    ) {

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
                text ="Settings",
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
//          modifier = Modifier.padding(8.dp)
                modifier = Modifier
//                    .align(End),
                    .align(CenterHorizontally),
            )

            //===============================
//        bottomSheet(SettingFlow.Language.route) {
            val viewModel = hiltViewModel<LocalizedSettingViewModel>()
//            bottomSheetConfig.value = DefaultMainBottomSheetConfig

            LanguageScreen(
                viewModel = viewModel,
                onClickBack = {  }
//              onClickBack = { navController.navigateUp() }
            )


            //       }

            //=============================

        }

    }
    if (currentRoute == Routes.SETTINGAPPMETRICA_ROUTE) {
 //       SettingsAppMetricaComponent(context)
        SettingsAppMetricaComponent()
    }

}

/*
    if (currentRoute == Routes.SETTINGS_ROUTE) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(0.dp, changeDp, 0.dp, 0.dp)
                    .fillMaxSize()
                    .background(Color(0xffFF6F00))
            ) {
                Text(
                    color = Color.White,
                    text = "Settings",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
//        Toast.makeText(context, "SettingsComponent: ", Toast.LENGTH_LONG).show()
    }
    if (currentRoute == Routes.SETTINGAPPMETRICA_ROUTE) {
        SettingsAppMetricaComponent(context)
    }

 */

@Composable
fun SettingsAppMetricaComponent() {
//fun SettingsAppMetricaComponent(context: Context) {
    val changeDp: Dp

    changeDp = 0.dp

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(0.dp, changeDp, 0.dp, 0.dp)
            .fillMaxSize()
            .background(Color(0xffFF6F00))
    ) {
//      Spacer(modifier = Modifier.height(4.dp))
        Text(
            color = Color.White,
            text = "Yandex AppMetrica \n",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
//        Text(color = Color.White, text = "SettingsAPPMetrica", fontSize = 50.sp, fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.height(20.dp))
    Text(color = Color.Black, text = "\n\n Это приложение использует сервис аналитики AppMetrica (Яндекс.Метрика для приложений), предоставляемый компанией ООО «ЯНДЕКС», 119021, Россия, Москва, ул. Л. Толстого, 16 (далее — Яндекс) на Условиях использования сервиса.\n" +
            "\n" +
            "AppMetrica анализирует данные об использовании приложения, в том числе об устройстве, на котором оно функционирует, источнике установки, составляет конверсию и статистику вашей активности\n" +
            "в целях продуктовой аналитики, анализа и оптимизации рекламных кампаний, а также для устранения ошибок. Собранная таким образом информация не может идентифицировать вас.\n" +
            "\n" +
            "Информация об использовании вами данного приложения, собранная при помощи инструментов AppMetrica, в обезличенном виде будет передаваться Яндексу и храниться на сервере Яндекса в ЕС и Российской Федерации.\n" +
            "Яндекс будет обрабатывать эту информацию для предоставления статистики использования вами приложения, составления для нас отчетов о работе приложения, и предоставления других услуг.\n",
        fontSize = 14.sp, fontWeight = FontWeight.Normal)

//    YandexMetrica.setStatisticsSending(context, true)
//    Toast.makeText(context, "SettingsAppMetricaComponent:YandexMetrica.setStatisticsSending(context, true) ", Toast.LENGTH_LONG).show()

/*
После того как пользователь дал согласие на отправку статистики (например, в настройках приложения или в соглашении при первом открытии), включите отправку статистики
с помощью метода YandexMetrica.setStatisticsSending(Context context, boolean enabled):
// Checking the status of the boolean variable. It shows the user confirmation.
if (flag) {
    // Enabling sending statistics.
    YandexMetrica.setStatisticsSending(getApplicationContext(), true);
}
Пример оповещения
Для информирования пользователей вы можете использовать любой текст. Например:

Это приложение использует сервис аналитики AppMetrica (Яндекс.Метрика для приложений), предоставляемый компанией ООО «ЯНДЕКС», 119021, Россия, Москва, ул. Л. Толстого, 16 (далее — Яндекс) на Условиях использования сервиса.

AppMetrica анализирует данные об использовании приложения, в том числе об устройстве, на котором оно функционирует, источнике установки, составляет конверсию и статистику вашей активности
в целях продуктовой аналитики, анализа и оптимизации рекламных кампаний, а также для устранения ошибок. Собранная таким образом информация не может идентифицировать вас.

Информация об использовании вами данного приложения, собранная при помощи инструментов AppMetrica, в обезличенном виде будет передаваться Яндексу и храниться на сервере Яндекса в ЕС и Российской Федерации.
Яндекс будет обрабатывать эту информацию для предоставления статистики использования вами приложения, составления для нас отчетов о работе приложения, и предоставления других услуг.
 */

}
