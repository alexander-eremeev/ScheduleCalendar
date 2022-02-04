package com.childmathematics.android.shiftschedule.navigation

//import com.childmathematics.android.shiftschedule.navigation.ui.ComposeTutorialsTheme
//import com.childmathematics.android.shiftschedule.navigation.ui.components.DrawerButton
//import com.childmathematics.android.shiftschedule.html.loadWebUrl
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.shiftschedule.BuildConfig
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.ViewModelSample
import com.childmathematics.android.shiftschedule.shiftads.mInterstitialAdOnOff
import com.childmathematics.android.shiftschedule.navigation.model.ActionItemMode
import com.childmathematics.android.shiftschedule.navigation.model.ActionItemSpec
import com.childmathematics.android.shiftschedule.navigation.model.separateIntoActionAndOverflow
import com.childmathematics.android.shiftschedule.navigation.ui.components.DrawerButton
import com.childmathematics.android.shiftschedule.shiftads.AdBannerNetworkApp
import com.childmathematics.android.shiftschedule.shiftads.AdInterstitialNetworkApp
import com.childmathematics.android.shiftschedule.shiftads.AdNetworkApp
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoScreen
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoViewModel
import com.childmathematics.android.shiftschedule.webview.WebViewMainScreen
import com.childmathematics.android.shiftschedule.webview.ui.theme.WebViewTheme
import com.yandex.metrica.YandexMetrica
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


//import com.childmathematics.android.shiftschedule.html.ui.application
@ExperimentalCoroutinesApi
@Composable
fun NavigateScreen1(todoViewModel: TodoViewModel) {
    NavigateContent(todoViewModel)
}

@ExperimentalCoroutinesApi
@Composable
private fun NavigateContent(todoViewModel: TodoViewModel) {
    var currentRoute by remember { mutableStateOf(Routes.HOME_ROUTE) }
//    var currentAboutRoute by remember { mutableStateOf(AboutRoutes.ABOUTHELP_ROUTE) }
//    var currentAboutRoute : Int
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val openDrawer: () -> Unit = { coroutineScope.launch { scaffoldState.drawerState.open() } }
    val closeDrawer: () -> Unit = { coroutineScope.launch { scaffoldState.drawerState.close() } }

//    val context = LocalContext.current
//========Классическое меню ABOUT  1 ====в правом углу====================
    val aboutitems = listOf(
        ActionItemSpec("Помощь", Icons.Default.Help, ActionItemMode.ALWAYS_SHOW) {
            currentRoute =Routes.ABOUTHELP_ROUTE
        },
        ActionItemSpec("Лицензии", Icons.Default.Apps, ActionItemMode.IF_ROOM) {
//            currentRoute =Routes.ABOUTLICENSES_ROUTE
            currentRoute =Routes.ABOUTLICENSES_ROUTE
        },
        ActionItemSpec("Политика конфиденциальности", Icons.Default.LocalPolice, ActionItemMode.IF_ROOM) {
            currentRoute =Routes.ABOUTPOLICE_ROUTE

        },
    )
//========Классическое меню Графики  1  =======================
/*
    val scheduleitems = listOf(
        ActionItemSpec("Помощь", Icons.Default.Help, ActionItemMode.ALWAYS_SHOW) {
            currentRoute =Routes.ABOUTHELP_ROUTE
        },
        ActionItemSpec("Лицензии", Icons.Default.Apps, ActionItemMode.IF_ROOM) {
            currentRoute =Routes.ABOUTLiSENSES_ROUTE

        },
    )
*/
//========Классическое меню Настройки  1 =======================

    val settingitems = listOf(
        ActionItemSpec("Yandex AppMetrica", Icons.Default.AppSettingsAlt, ActionItemMode.ALWAYS_SHOW) {
            currentRoute =Routes.SETTINGAPPMETRICA_ROUTE

        },
    )
    val settingappmetricaitems = listOf(
        ActionItemSpec("Настройки", Icons.Default.Settings, ActionItemMode.ALWAYS_SHOW) {
            currentRoute =Routes.SETTINGS_ROUTE
        },
    )

//------------------------------------

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
             AppDrawer(
                currentRoute = currentRoute,
                navigateToHome = {
                    currentRoute = Routes.HOME_ROUTE
                    navController.popBackStack()
                    navController.navigate(currentRoute)
                },
                navigateToSchedule = {
                    currentRoute = Routes.SCHEDULE_ROUTE
                    navController.popBackStack()
                    navController.navigate(currentRoute)
                },
                navigateToToDo = {
                    currentRoute = Routes.TODO_ROUTE
                    navController.popBackStack()
                    navController.navigate(currentRoute)
                },
                navigateToSettings = {
                    currentRoute = Routes.SETTINGS_ROUTE
                    navController.popBackStack()
                    navController.navigate(currentRoute)
                },
                navigateToAbout = {
                    currentRoute = Routes.ABOUT_ROUTE
                    navController.popBackStack()
                    navController.navigate(currentRoute)
                },
                navigateToAdMob = {
                    currentRoute = Routes.ADMOB_ROUTE
                    navController.popBackStack()
                    navController.navigate(currentRoute)
                },
/*
                navigateToAboutHelp = {
                    currentRoute = Routes.ABOUTHELP_ROUTE
                    navController.popBackStack()
                    navController.navigate(currentRoute)
                },
*/
                closeDrawer = closeDrawer
            )
        },

        topBar = {
            TopAppBar(
                title = {
                    Text("Производственный календарь")
                },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null
                        )
                    }
                },
                actions = {
// 1.  второстепенное меню в правом углу
//========================================================
                    if (currentRoute == Routes.SCHEDULE_ROUTE) {
                        OverflowTopSchedule()
                    }
//========Классическое меню ABOUT  2 ==в правом углу======================
                    if (currentRoute == Routes.ABOUT_ROUTE) {
                        ActionMenu(aboutitems, defaultIconSpace = 3)
                    }
                    if (currentRoute == Routes.ABOUTHELP_ROUTE) {
                        ActionMenu(aboutitems, defaultIconSpace = 3)
                    }
                    if (currentRoute == Routes.ABOUTLICENSES_ROUTE) {
                        ActionMenu(aboutitems, defaultIconSpace = 3)
                    }
                    if (currentRoute == Routes.ABOUTPOLICE_ROUTE) {
                        ActionMenu(aboutitems, defaultIconSpace = 3)
                    }
//========Классическое меню Настройки  2 ==в правом углу======================
                    if (currentRoute == Routes.SETTINGS_ROUTE) {
                        ActionMenu(settingitems, defaultIconSpace = 3)
                    }
                    if (currentRoute == Routes.SETTINGAPPMETRICA_ROUTE) {
                        ActionMenu(settingappmetricaitems, defaultIconSpace = 3)
                    }
//========================================================
                }
            )

        },

        snackbarHost = {
        },


        ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HOME_ROUTE
        ) {
            composable(Routes.HOME_ROUTE) {
                HomeComponent(LocalContext.current)
            }

//========Классическое меню Setting  3 ==в правом углу======================
            composable(Routes.SETTINGS_ROUTE) {
                SettingsComponent(LocalContext.current,currentRoute)
            }
            if (currentRoute == Routes.SETTINGAPPMETRICA_ROUTE) {
                composable(Routes.SETTINGAPPMETRICA_ROUTE) {
                    SettingsComponent(LocalContext.current,currentRoute)
//                SettingsAppMetricaComponent(LocalContext.current)
                }
            }



//+++++++++++++++++++++++++++++++++++++++++++++++++++++++
            composable(Routes.TODO_ROUTE) {
                TodoComponent(todoViewModel )
            }
//------------------------------------------------------
            composable(Routes.ADMOB_ROUTE) {
                AdMobComponent()
            }
//========Классическое меню ABOUT  3 ==в правом углу======================
            composable(Routes.ABOUT_ROUTE) {
                AboutComponent(currentRoute)
            }
//------------------------------------------------------
            composable(Routes.ABOUTHELP_ROUTE) {
                AboutComponent(currentRoute )
            }
//------------------------------------------------------
            composable(Routes.ABOUTLICENSES_ROUTE) {
              AboutComponent(currentRoute )
            }
//------------------------------------------------------
            composable(Routes.ABOUTPOLICE_ROUTE) {
                AboutComponent(currentRoute )
            }
//--------------------------------------------------------
            composable(Routes.SCHEDULE_ROUTE) {

                ScheduleComponent()
            }
//--------------------------------------------------------

        }
    }
}

/**
 * Content of side navigation
 */
@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToSettings: () -> Unit,
//+++++++++++++++++++++++
    navigateToToDo: () -> Unit,
    navigateToAdMob: () -> Unit,
    navigateToAbout: () -> Unit,
    navigateToSchedule: () -> Unit,
//-----------------------------------
    closeDrawer: () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        DrawerHeader()
        DrawerButton(
            icon = Icons.Filled.Home,
            label = "Главная",
            isSelected = currentRoute == Routes.HOME_ROUTE,
            action = {
                if (currentRoute != Routes.HOME_ROUTE) {
                    navigateToHome()
                }
                closeDrawer()
            }
        )
//---------------------------------------------------------------------------
        DrawerButton(
//            icon = Icons.Filled.Preview,
            icon = Icons.Filled.Schedule,
            label = "Графики смен",
            isSelected = currentRoute == Routes.SCHEDULE_ROUTE,
            action = {
                if (currentRoute != Routes.SCHEDULE_ROUTE) {
                    navigateToSchedule()
                }
                closeDrawer()
            }
        )
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        DrawerButton(
//            icon = Icons.Filled.Settings,
            icon = Icons.Filled.EventNote,
            label = "Записная книжка",
            isSelected = currentRoute == Routes.TODO_ROUTE,
            action = {
                if (currentRoute != Routes.TODO_ROUTE) {
                    navigateToToDo()
                }
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Icons.Filled.Settings,
            label = "Настройки",
            isSelected = currentRoute == Routes.SETTINGS_ROUTE,
            action = {
                if (currentRoute != Routes.SETTINGS_ROUTE) {
                    navigateToSettings()
                }
                closeDrawer()
            }
        )

//---------------------------------------------------------------------------
        DrawerButton(
//            icon = Icons.Filled.Preview,
//            painter = painterResource(id = R.drawable.ic_shiftschedule),

            icon = Icons.Filled.Apps,
//            icon = R.drawable.icon8-aboput-64.png,
            label = "О приложении",
            isSelected = currentRoute == Routes.ABOUT_ROUTE,
            action = {
                if (currentRoute != Routes.ABOUT_ROUTE) {
                    navigateToAbout()
                }
                closeDrawer()
            }
        )
//-----------------------------------------------------------------------
        DrawerButton(
            icon = Icons.Filled.AdUnits,
            label = "AdMob",
            isSelected = currentRoute == Routes.ADMOB_ROUTE,
            action = {
                if (currentRoute != Routes.ADMOB_ROUTE) {
                    navigateToAdMob()
                }
                closeDrawer()
            }
        )
//---------------------------------------------------------------------------

    }
}
/*
@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun AppDrawerPreview() {
    ComposeTutorialsTheme {
        AppDrawer(
            currentRoute = Routes.HOME_ROUTE,
            navigateToHome = {},
            navigateToSettings = {},
            closeDrawer = {}
        )
    }
}
*/
@Composable
private fun DrawerHeader() {

    Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.background(Color.Green)) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp),
            painter = painterResource(id = R.drawable.drawer_bg2),
            contentDescription = null
        )

        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
//                painter = painterResource(id = R.drawable.avatar_1_raster),
                painter = painterResource(id = R.drawable.ic_shiftschedule),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Производственный календарь", color = Color.White)
            Text(text = "trening.childmathematics@gmail.com", color = Color.White)
        }
    }
}
/*
@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun DrawerHeaderPreview() {
    ComposeTutorialsTheme {
        DrawerHeader()
    }
}
*/
/*
@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun DrawerButtonPreview() {
    ComposeTutorialsTheme {
        DrawerButton(
            icon = Icons.Filled.Home,
            label = "Home",
            isSelected = true,
            action = {

            }
        )
    }
}
*/
@Composable
fun HomeComponent(context: Context) {


    AdBannerNetworkApp()
    AdInterstitialNetworkApp(context)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(0.dp,50.dp,0.dp,0.dp)
            .fillMaxSize()
            .background(Color(0xff6D4C41))
    ) {
        Text(color = Color.White, text = "Home", fontSize = 50.sp, fontWeight = FontWeight.Bold)
    }

}
/*
@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun HomeComponentPreview() {
    ComposeTutorialsTheme {
        HomeComponent()
    }
}
*/
/*
@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun SettingsComponentPreview() {
    ComposeTutorialsTheme {
        SettingsComponent()
    }
}
*/

object Routes {
    const val HOME_ROUTE = "Home"           //Home   Титульный лист
    //------------------меню   4 ==в правом угл---------------------------------------
    const val SETTINGS_ROUTE = "Settings"   //Settings Настройки
    const val SETTINGAPPMETRICA_ROUTE = "SETTINGAPPMETRICA"
    //---------------------------------------------------------
    const val TODO_ROUTE = "ToDo"
    const val ADMOB_ROUTE = "AdMob"
    const val YANDEX_ADS_ROUTE = "YandexAds"
    //========Классическое меню ABOUT  4 ==в правом углу======================
    const val ABOUT_ROUTE = "About"
    const val ABOUTHELP_ROUTE = "AboutHelp"
    const val ABOUTLICENSES_ROUTE = "AboutLicenses"
    const val ABOUTPOLICE_ROUTE = "AboutPolice"
    //---------------------------------------------------------
    const val SCHEDULE_ROUTE = "Schedule"
    //---------------------------------------------------------
}

//++++++++++++++++++++++++++++++++++++
@Composable
fun TodoComponent(todoViewModel: TodoViewModel) {
    AdBannerNetworkApp()
    AdInterstitialNetworkApp(LocalContext.current)
//        .padding(0.dp,50.dp,0.dp,0.dp)
    TodoScreen(
        items = todoViewModel.todoItems,
        currentlyEditing = todoViewModel.currentEditItem,
        onAddItem = todoViewModel::addItem,
        onRemoveItem = todoViewModel::removeItem,
        onStartEdit = todoViewModel::onEditItemSelected,
        onEditItemChange = todoViewModel::onEditItemChange,
        onEditDone = todoViewModel::onEditDone
    )
}
/*

 */
//++++++++++++++++++++++++++++++++++++
@Composable
fun AdMobComponent() {
    AdNetworkApp()
}
//-----------------------------------
//========Классическое меню ABOUT  5 ==в правом углу======================
@Composable
fun AboutComponent(currentRoute : String) {
    AdBannerNetworkApp()
    AdInterstitialNetworkApp(LocalContext.current)
//    val versionCode = BuildConfig.VERSION_CODE
    val yearStr = BuildConfig.BUILD_TIMESTAMP
    val versionName = BuildConfig.VERSION_NAME
//    fun AboutComponent(currentRoute : String,aboutitems : List<ActionItemSpec>) {
        WebViewTheme {
//            WebViewMainScreen("http://www.bbc.com")
            WebViewMainScreen("file:///android_asset/about.html?Version=$versionName&Year=$yearStr")
        }
    if (currentRoute == Routes.ABOUTHELP_ROUTE) {

        WebViewTheme {
//            WebViewMainScreen("http://www.bbcrussian.com")
            WebViewMainScreen("file:///android_asset/help.html")
        }
    }
    if (currentRoute == Routes.ABOUTLICENSES_ROUTE) {

        WebViewTheme {
//            WebViewMainScreen("http://www.cnn.com")
        WebViewMainScreen("file:///android_asset/open_source_licenses.html")
        }
    }
    if (currentRoute == Routes.ABOUTPOLICE_ROUTE) {

        WebViewTheme {
//            WebViewMainScreen("http://www.cnn.com")
            WebViewMainScreen("file:///android_asset/privacy_police_app.html")
        }
    }

}
//-----------------------------------
//========Классическое меню 5 ==в правом углу======================
@Composable
fun SettingsComponent(context: Context,currentRoute : String) {
    AdBannerNetworkApp()
    AdInterstitialNetworkApp(LocalContext.current)

    if (currentRoute == Routes.SETTINGS_ROUTE) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(0.dp,50.dp,0.dp,0.dp)
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

}
@Composable
fun SettingsAppMetricaComponent(context: Context) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(0.dp,50.dp,0.dp,0.dp)
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
    }
    YandexMetrica.setStatisticsSending(context, true)
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

//++++++++++++++++++++++++++++++++++++
@ExperimentalCoroutinesApi
@Composable
fun ScheduleComponent() {
    AdBannerNetworkApp()
    AdInterstitialNetworkApp(LocalContext.current)

    ViewModelSample()
 //   StaticCalendarSamOverflowTopSchedule()

}
@Composable
fun OverflowTopSchedule() {
    val items = listOf(
        ActionItemSpec("Call", Icons.Default.Call, ActionItemMode.ALWAYS_SHOW) {},
        ActionItemSpec("Send", Icons.Default.Send, ActionItemMode.IF_ROOM) {},
        ActionItemSpec("Email", Icons.Default.Email, ActionItemMode.IF_ROOM) {},
        ActionItemSpec("Delete", Icons.Default.Delete, ActionItemMode.IF_ROOM) {},
    )
            ActionMenu(items, defaultIconSpace = 3)
}

//==============================================
//===================================================
/*
@Composable
fun OverflowTopAppBar2() {
    val items = listOf(
        ActionItemSpec("Call", Icons.Default.Call, ActionItemMode.ALWAYS_SHOW) {},
        ActionItemSpec("Send", Icons.Default.Send, ActionItemMode.IF_ROOM) {},
        ActionItemSpec("Email", Icons.Default.Email, ActionItemMode.IF_ROOM) {},
        ActionItemSpec("Delete", Icons.Default.Delete, ActionItemMode.IF_ROOM) {},
    )
    TopAppBar(
        title = { Text("Overflow2") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Menu, "Menu")
            }
        },
        actions = {
            ActionMenu(items, defaultIconSpace = 3)
        }

    )
//-----------------------------

//==========================
}
*/
@Composable
fun ActionMenu(
    items: List<ActionItemSpec>,
    defaultIconSpace: Int = 3, // includes overflow menu
    menuExpanded: MutableState<Boolean> = remember { mutableStateOf(false) }
) {
    // decide how many ifRoom icons to show as actions
    val (actionItems, overflowItems) = remember(items, defaultIconSpace) {
        separateIntoActionAndOverflow(items, defaultIconSpace)
    }

    Row {
        for (item in actionItems) {
            IconButton(onClick = item.onClick) {
                Icon(item.icon, item.name)
            }
        }
        if (overflowItems.isNotEmpty()) {
            IconButton(onClick = { menuExpanded.value = true }) {
                Icon(Icons.Default.MoreVert, "More actions")
            }
            DropdownMenu(
                expanded = menuExpanded.value,
                onDismissRequest = { menuExpanded.value = false }
            ) {
                for (item in overflowItems) {
                    DropdownMenuItem(onClick = item.onClick) {
                        //Icon(item.icon, item.name) just have text in the overflow menu
                        Text(item.name)
                    }
                }
            }
        }
    }
}
//-----------------------------------------------------------------------
