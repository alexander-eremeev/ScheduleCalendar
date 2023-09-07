package com.childmathematics.android.shiftschedule.navigation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.childmathematics.android.shiftschedule.*
import com.childmathematics.android.shiftschedule.R
import com.childmathematics.android.shiftschedule.mainpage.HomePageShow
import com.childmathematics.android.basement.lib.navigation.model.ActionItemMode
import com.childmathematics.android.basement.lib.navigation.model.ActionItemSpec
import com.childmathematics.android.basement.lib.navigation.model.separateIntoActionAndOverflow
import com.childmathematics.android.basement.lib.navigation.ui.bottomapp.BottomMenu.getMenuListschedule500
import com.childmathematics.android.basement.lib.navigation.ui.components.DrawerButton
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoScreen
import com.childmathematics.android.shiftschedule.shifttodo.todo.TodoViewModel
import com.childmathematics.android.shiftschedule.webview.WebViewMainScreen
import com.childmathematics.android.basement.lib.webview.ui.theme.WebViewTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import com.childmathematics.android.basement.lib.ads.admob.AdBannerNetworkApp
import com.childmathematics.android.basement.lib.ads.admob.AdInterstitialNetworkApp
import com.childmathematics.android.basement.lib.ads.util.detectTapAndPressUnconsumed
import com.childmathematics.android.basement.lib.ads.yandex.ShowYaInterstitial
import com.childmathematics.android.basement.lib.ads.yandex.YANDEX_MOBILE_ADS_TAG
import com.childmathematics.android.basement.lib.ads.yandex.yaAdsInterstutialTimerOff
import com.childmathematics.android.shiftschedule.mainpage.SettingPageShow


@ExperimentalCoroutinesApi
@Composable
fun NavigateScreen1(todoViewModel: TodoViewModel) {
    NavigateContent(todoViewModel)
}

@ExperimentalCoroutinesApi
@Composable
private fun NavigateContent(todoViewModel: TodoViewModel) {
    val currentRoute = rememberSaveable { mutableStateOf(Routes.HOME_ROUTE) }
    val currentAboutRoute = rememberSaveable { mutableStateOf(AboutRoutes.ABOUTNULL_ROUTE)}
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val openDrawer: () -> Unit = { coroutineScope.launch { scaffoldState.drawerState.open() } }
    val closeDrawer: () -> Unit = { coroutineScope.launch { scaffoldState.drawerState.close() } }
    var currentDialog by rememberSaveable { mutableStateOf(false) }


//========Классическое меню ABOUT  1 ====в правом углу====================
    val aboutitems = listOf(
        ActionItemSpec("Помощь", Icons.Default.Help, ActionItemMode.ALWAYS_SHOW) {
            if (currentAboutRoute.value ==AboutRoutes.ABOUTHELP_ROUTE)
                currentAboutRoute.value =AboutRoutes.ABOUTNULL_ROUTE
            else
                currentAboutRoute.value =AboutRoutes.ABOUTHELP_ROUTE
        },
        ActionItemSpec("Лицензии", Icons.Default.Apps, ActionItemMode.IF_ROOM) {
            if (currentAboutRoute.value ==AboutRoutes.ABOUTLICENSES_ROUTE)
                currentAboutRoute.value =AboutRoutes.ABOUTNULL_ROUTE
            else
                currentAboutRoute.value =AboutRoutes.ABOUTLICENSES_ROUTE
        },
        ActionItemSpec("Политика конфиденциальности", Icons.Default.LocalPolice, ActionItemMode.IF_ROOM) {
            if (currentAboutRoute.value ==AboutRoutes.ABOUTPOLICE_ROUTE)
                currentAboutRoute.value =AboutRoutes.ABOUTNULL_ROUTE
            else
                currentAboutRoute.value =AboutRoutes.ABOUTPOLICE_ROUTE

        },
    )

//------------------------------------------------------------------------

    val schedule500items = listOf(
        ActionItemSpec("Расчет для выделенных дат", Icons.Default.Summarize, ActionItemMode.IF_ROOM) {
            currentDialog= !currentDialog
        },
    )

//    currentRoute = currentRou.value

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            ,
        drawerGesturesEnabled= false,                    // поддержка жестов
        drawerContent = {
             AppDrawer(
                currentRoute = currentRoute.value,
                navigateToHome = {
                    currentRoute.value = Routes.HOME_ROUTE
                    if (BuildConfig.HomeRouteEnable) {
                        navController.popBackStack()
                        navController.navigate(currentRoute.value)
                    }
                },
                navigateToSchedule = {//&& BuildConfig.ScheduleRouteEnable
                    currentRoute.value = Routes.SCHEDULE_ROUTE
                    if (BuildConfig.ScheduleRouteEnable) {
                        navController.popBackStack()
                        navController.navigate(currentRoute.value)
                    }
                },
                 navigateToSchedule500 = {
                     currentRoute.value = Routes.SCHEDULE500_ROUTE

                     if (BuildConfig.Schedule500RouteEnable) {
                         navController.popBackStack()
                         navController.navigate(currentRoute.value)
                     }
                 },
                 navigateToSchedule01 = {
                     currentRoute.value = Routes.SCHEDULE01_ROUTE
                     if (BuildConfig.Schedule01RouteEnable) {
                         navController.popBackStack()
                         navController.navigate(currentRoute.value)
                     }
                 },
                navigateToToDo = {
                    currentRoute.value = Routes.TODO_ROUTE
                    if (BuildConfig.ToDoRouteEnable) {
                        navController.popBackStack()
                        navController.navigate(currentRoute.value)
                    }
                    navController.navigate(currentRoute.value)
                },

                navigateToSettings = {
                    currentRoute.value = Routes.SETTINGS_ROUTE
                    if (BuildConfig.SettingsRouteEnable) {
                        navController.popBackStack()
                        navController.navigate(currentRoute.value)
                    }
                },
                navigateToAbout = {
                    currentRoute.value = Routes.ABOUT_ROUTE
                    navController.popBackStack()
                    navController.navigate(currentRoute.value)
                },
                closeDrawer = closeDrawer
            )
        },

        topBar = {
//================================================================
            TopAppBar(
                title = {
                    Text("Рабочий календарь",
                        fontSize = 15.sp ,
                    )
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
//========================================================
                    if (currentRoute.value == Routes.SCHEDULE500_ROUTE && BuildConfig.Schedule500RouteEnable) {
                        ActionMenu(schedule500items, defaultIconSpace = 0)
                    }
//========================================================
// 1.  второстепенное меню в правом углу

//========================================================
                    if (currentRoute.value == Routes.SCHEDULE01_ROUTE && BuildConfig.Schedule01RouteEnable) {
                        ActionMenu(schedule500items, defaultIconSpace = 0)
                    }

//========Классическое меню ABOUT  2 ==в правом углу======================
                    if (currentRoute.value == Routes.ABOUT_ROUTE) {
                        ActionMenu(aboutitems, defaultIconSpace = 3)
                    }
                }
            )
//===============================================================
        },
        bottomBar = {

        },

        snackbarHost = {
        },


        ) {
            contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)
                                        ) { /* ... */        }
        NavHost(
            navController = navController,
            startDestination = Routes.HOME_ROUTE
        ) {
            composable(Routes.HOME_ROUTE) {
                HomeComponent()
            }
//========Классическое меню Setting  3 ==в правом углу======================
            if (BuildConfig.SettingsRouteEnable) {
                composable(Routes.SETTINGS_ROUTE) {
//                    SettingsComponent(LocalContext.current, currentRoute.value)
                        SettingsComponent( currentRoute.value)
                }
            }
            if (currentRoute.value == Routes.SETTINGAPPMETRICA_ROUTE && BuildConfig.AppMetricaOn && BuildConfig.SettingsRouteEnable) {
                composable(Routes.SETTINGAPPMETRICA_ROUTE) {
//                    SettingsComponent(LocalContext.current,currentRoute.value)
                    SettingsComponent(currentRoute.value)
//                SettingsAppMetricaComponent(LocalContext.current)
                }
            }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++
            if (BuildConfig.ToDoRouteEnable) {
                composable(Routes.TODO_ROUTE) {
                    TodoComponent(todoViewModel)
                }
            }
//------------------------------------------------------
            /*
            composable(Routes.ADMOB_ROUTE) {
                AdMobComponent()
            }

             */
//========Классическое меню ABOUT  3 ==в правом углу======================
            composable(Routes.ABOUT_ROUTE) {
                AboutComponent(currentAboutRoute.value)
            }
//------------------------------------------------------
            if (BuildConfig.ScheduleRouteEnable) {
                composable(Routes.SCHEDULE_ROUTE) {

                    ScheduleComponent()
                }
            }
//--------------------------------------------------------
            if (BuildConfig.Schedule500RouteEnable) {
                composable(Routes.SCHEDULE500_ROUTE) {

                    Schedule500Component(currentDialog)
                }
            }
//--------------------------------------------------------
            if (BuildConfig.Schedule01RouteEnable) {
                composable(Routes.SCHEDULE01_ROUTE) {

                    Schedule01Component(currentDialog)
                }
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
//    navigateToAdMob: () -> Unit,
    navigateToAbout: () -> Unit,
    navigateToSchedule: () -> Unit,
    navigateToSchedule500: () -> Unit,
    navigateToSchedule01: () -> Unit,
//-----------------------------------
    closeDrawer: () -> Unit
) {
//------------------------------------------------------------------------

//    Column(modifier = Modifier.scrollable()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

        //====================================================
        // фиксация нажатия экрана для сдвига паказа рекламы
            .pointerInput(Unit) {
                detectTapAndPressUnconsumed(onTap = {
                    Log.d(YANDEX_MOBILE_ADS_TAG, "SideNavigationScaffold  Interstitial: TAP")
//?                    yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
                })
            }
        //--------------------------------------------------


        ) {
            DrawerHeader()

            if (BuildConfig.HomeRouteEnable) {
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
            }
//---------------------------------------------------------------------------
        if (BuildConfig.ScheduleRouteEnable) {

            DrawerButton(
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
        }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            if (BuildConfig.Schedule01RouteEnable) {

                DrawerButton(
                    icon = Icons.Filled.Schedule,
                    label = "График прерывный, односменный, 8 часовой с выходными днями суббота,воскресенье (В РАЗРАБОТКЕ)",
                    isSelected = currentRoute == Routes.SCHEDULE01_ROUTE,
                    action = {
                        if (currentRoute != Routes.SCHEDULE01_ROUTE) {
                            navigateToSchedule01()
                        }
                        closeDrawer()
                    }
                )
            }

//---------------------------------------------------------------------
            if (BuildConfig.Schedule500RouteEnable) {

                DrawerButton(
                    icon = Icons.Filled.Schedule,
                    label = "График непрерывный 12 часовой 4-х бригадный 2-х сменный",
                    isSelected = currentRoute == Routes.SCHEDULE500_ROUTE,
                    action = {
                        if (currentRoute != Routes.SCHEDULE500_ROUTE) {
                            navigateToSchedule500()
                        }
                        closeDrawer()
                    }
                )
            }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        if (BuildConfig.ToDoRouteEnable) {

            DrawerButton(
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
        }
//---------------------------------------------------------------
        if (BuildConfig.SettingsRouteEnable) {
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
        }

//---------------------------------------------------------------------------
        DrawerButton(
//            icon = Icons.Filled.Preview,
//            painter = painterResource(id = R.drawable.ic_shiftschedule),

            icon = Icons.Filled.Apps,
            label = "О приложении",
            isSelected = currentRoute == Routes.ABOUT_ROUTE,
            action = {
                if (currentRoute != Routes.ABOUT_ROUTE) {
                    navigateToAbout()
                }
                closeDrawer()
            }
        )

    }
}
@Composable
private fun DrawerHeader() {
    //var thisScreenWidthDp: Int

    //thisScreenWidthDp = LocalConfiguration.current.screenWidthDp

    Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.background(Color.DarkGray)) {
        Image(
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
//                .padding(80.dp,0.dp,(thisScreenWidthDp-80).dp,50.dp)
                .padding(80.dp,0.dp,0.dp,50.dp)
                .height(160.dp)
//                .padding(80.dp,0.dp,0.dp,45.dp)
            ,
//            painter = painterResource(id = R.drawable.work_1280),
            painter = painterResource(id = R.drawable.powering_idea_4),
            contentDescription = null
        )

        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
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


@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun DrawerButtonPreview() {
    ComposeTutorialsTheme {
        DrawerButton(
            icon = Icons.Filled.Home,
            label = "Главная",
            isSelected = true,
            action = {

            }
        )
    }
}
*/
//========================================================================
@Composable
fun HomeComponent() {
//    var changeDp: Dp

    if(BuildConfig.AdMobEnable) {
        AdBannerNetworkApp()
        AdInterstitialNetworkApp(LocalContext.current)
    }
    if (BuildConfig.YaAdsEnable) {

        /*
        InitBannerView(mBannerAdEventListener)

        ShowYaInterstitial()

         */
    }
    HomePageShow()
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
//=======================================================================

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
    //---------------------------------------------------------
    const val SCHEDULE_ROUTE = "Schedule"
    const val SCHEDULE01_ROUTE = "Schedule01"
    const val SCHEDULE500_ROUTE = "Schedule500"
    //---------------------------------------------------------
}
object AboutRoutes {
    const val ABOUTNULL_ROUTE = ""
    const val ABOUTHELP_ROUTE = "AboutHelp"
    const val ABOUTLICENSES_ROUTE = "AboutLicenses"
    const val ABOUTPOLICE_ROUTE = "AboutPolice"
}
//++++++++++++++++++++++++++++++++++++
@Composable
fun TodoComponent(todoViewModel: TodoViewModel) {
    if (BuildConfig.AdMobEnable){
        AdBannerNetworkApp()
        AdInterstitialNetworkApp(LocalContext.current)
    }
    if (BuildConfig.YaAdsEnable) {
 //       InitBannerView(mBannerAdEventListener)
        ShowYaInterstitial()
    }


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
//========Классическое меню ABOUT  5 ==в правом углу======================
@Composable
fun AboutComponent(currentAboutRoute : String) {
    if (BuildConfig.AdMobEnable) {
        AdBannerNetworkApp()
        AdInterstitialNetworkApp(LocalContext.current)
    }
    /*
    if (BuildConfig.YaAdsEnable) {
//        initInterstitialAd()
 //       InitBannerView(mBannerAdEventListener)
 //       ShowYaInterstitial()
    }
     */

//    val versionCode = BuildConfig.VERSION_CODE
    val yearStr = BuildConfig.BUILD_TIMESTAMP
    val versionName = BuildConfig.VERSION_NAME
    WebViewTheme {
//            WebViewMainScreen("http://www.bbc.com")
            WebViewMainScreen("file:///android_asset/about.html?Version=$versionName&Year=$yearStr")
//            WebViewMainScreen("file:///android_asset/about.html?Version=$BuildConfig.VERSION_NAME&Year=$yearStr")
        }
    if (currentAboutRoute == AboutRoutes.ABOUTHELP_ROUTE) {

        WebViewTheme {
//            WebViewMainScreen("http://www.bbcrussian.com")
            WebViewMainScreen("file:///android_asset/help.html")
        }
    }
    if (currentAboutRoute == AboutRoutes.ABOUTLICENSES_ROUTE) {

        WebViewTheme {
//            WebViewMainScreen("http://www.cnn.com")
        WebViewMainScreen("file:///android_asset/open_source_licenses.html")
        }
    }
    if (currentAboutRoute == AboutRoutes.ABOUTPOLICE_ROUTE) {

        WebViewTheme {
//            WebViewMainScreen("http://www.cnn.com")
            WebViewMainScreen("file:///android_asset/privacy_police_app.html")
        }
    }

}
//-----------------------------------
//========Классическое меню 5 ==в правом углу======================
@Composable
//fun SettingsComponent(context: Context,currentRoute : String) {
fun SettingsComponent(currentRoute : String) {



    if (currentRoute == Routes.SETTINGS_ROUTE)
//        SettingPageShow(context,currentRoute)
        SettingPageShow(currentRoute)

//        Toast.makeText(context, "SettingsComponent: ", Toast.LENGTH_LONG).show()
}

//++++++++++++++++++++++++++++++++++++
@ExperimentalCoroutinesApi
@Composable
fun ScheduleComponent() {
    if (BuildConfig.AdMobEnable) {

        AdBannerNetworkApp()
        AdInterstitialNetworkApp(LocalContext.current)
    }
    if (BuildConfig.YaAdsEnable) {
//        InitBannerView(mBannerAdEventListener)
        ShowYaInterstitial()
    }
//    ViewModelSample()
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
/*
@Composable
fun OverflowTopSchedule500() {
    val schedule500items = listOf(
        ActionItemSpec("Расчет для выделенных дат", Icons.Default.Summarize, ActionItemMode.IF_ROOM) {
//            currentDialog= true
            currentDialog=!currentDialog
          /*
            if (currentRouteSchedule500 == RoutesSchedule500.SCHEDULE500SELSINGLE){
                currentRouteSchedule500 = RoutesSchedule500.SCHEDULE500SELPERIOD

            }
            else {
                currentRouteSchedule500 = RoutesSchedule500.SCHEDULE500SELSINGLE
            }

           */



        },
    )
    ActionMenu(schedule500items, defaultIconSpace = 0)
}
*/
//==============================================
//++++++++++++++++++++++++++++++++++++
@ExperimentalCoroutinesApi
@Composable
    fun Schedule500Component(currentDialog: Boolean) {

            if (BuildConfig.DEBUG) {
                Log.d ("Schedule500", "Schedule500Component")
            }
    if (BuildConfig.AdMobEnable) {

        AdBannerNetworkApp()
        AdInterstitialNetworkApp(LocalContext.current)
    }
    if (BuildConfig.YaAdsEnable) {
 //       InitBannerView(mBannerAdEventListener)
//      InitBannerView(mBannerAdEventListener)
        ShowYaInterstitial()
    }
    Schedule500Sample(currentDialog)
}
//++++++++++++++++++++++++++++++++++++
@ExperimentalCoroutinesApi
@Composable
//fun Schedule500Component() {
fun Schedule01Component(currentDialog: Boolean) {

    if (BuildConfig.DEBUG) {
        Log.d ("Schedule01","Schedule01Component")
    }
    if (BuildConfig.AdMobEnable) {

        AdBannerNetworkApp()
        AdInterstitialNetworkApp(LocalContext.current)
    }
    if (BuildConfig.YaAdsEnable) {
        ShowYaInterstitial()
//        InitBannerView(mBannerAdEventListener)
    }
    Schedule01Sample(currentDialog)
}
/*
@Composable
fun OverflowTopSchedule500(currentRouteSchedule500 : String) {
//    const val SCHEDULE500SELSINGLE = "Schedule500SelSingle"
//    const val SCHEDULE500SELPERIOD = "Schedule500SelPeriod"

    ActionMenu(schedule500items, defaultIconSpace = 3)
}
*/
//==============================================
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
            IconButton(
                onClick = item.onClick) {
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
                    DropdownMenuItem(
                        modifier = Modifier
                            //====================================================
                            // фиксация нажатия экрана для сдвига паказа рекламы
                            .pointerInput(Unit) {
                                detectTapAndPressUnconsumed(onTap = {
                                    Log.d(YANDEX_MOBILE_ADS_TAG, "SideNavigationScaffold ActionMenu Interstitial:select date TAP")
//?                                    yaAdsInterstutialTimerOff()  //реклама через 180 cек  durationNoPushTastaturAds
                                })
                            }
                        //--------------------------------------------------
                        ,
                        onClick = item.onClick) {
                        //Icon(item.icon, item.name) just have text in the overflow menu
                        Text(item.name)

                    }
                }
            }
        }
    }
}
//-----------------------------------------------------------------------
@Composable
fun AppToolbar(scaffoldState: ScaffoldState, scope: CoroutineScope, toolbarTitle: String) {
    TopAppBar(
        title = { Text(text = toolbarTitle) },
        modifier = Modifier.background(Color(0xFF008800)),
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_star_12),
                contentDescription = "Menu", modifier = Modifier.clickable {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        actions = {
            val context = LocalContext.current
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_star_12),
                contentDescription = "Setting", modifier = Modifier.clickable {
                    Toast.makeText(context, "Open Setting", Toast.LENGTH_SHORT).show()
                }
            )
        }
    )
}

//-----------------------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun BottomAppBar() {
    /*
    data class MenuItem(val title: String, val icon: ImageVector)

    fun getMenuList() : List<MenuItem> {
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(MenuItem("Home", Icons.Filled.Home))
        menuItems.add(MenuItem("Search", Icons.Filled.Search))
        menuItems.add(MenuItem("Favorite", Icons.Filled.Favorite))
        menuItems.add(MenuItem("Settings", Icons.Filled.Settings))
        return menuItems
    }

     */

//    val bottomMenuList = getMenuList()  //getMenuListschedule500()
    val bottomMenuList = getMenuListschedule500()  //getMenuListschedule500()

    BottomNavigation {
        bottomMenuList.forEach { bottomMenu ->
            BottomNavigationItem(
                selected = bottomMenu.title == "Расчет для выделенных дат",
                onClick = {
//                    currentDialog= true
//                    currentDialog= !currentDialog
                    /*
                    if (currentRouteSchedule500_1 == RoutesSchedule500.SCHEDULE500SELSINGLE){
                        currentRouteSchedule500_2 = RoutesSchedule500.SCHEDULE500SELPERIOD

                    }
                    else {
                        currentRouteSchedule500 = RoutesSchedule500.SCHEDULE500SELSINGLE
                    }

                     */

                },
                icon = {
                    Icon(
                        imageVector = bottomMenu.icon,
                        contentDescription = bottomMenu.title
                    )
                })
        }
    }
}

//-----------------------------------------------------------------------
/*
@Composable
fun ComposeUnitConverterTopBar(menuItems: List<String>, onClick: (String) -> Unit) {
    var menuOpened by remember { mutableStateOf(false) }
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    },
        actions = {
            Box {
                IconButton(onClick = {
                    menuOpened = true
                }) {
                    Icon(Icons.Default.MoreVert, "")
                }
                DropdownMenu(expanded = menuOpened,
                    onDismissRequest = {
                        menuOpened = false
                    }) {
                    menuItems.forEachIndexed { index, s ->
                        if (index > 0) Divider()
                        DropdownMenuItem(onClick = {
                            menuOpened = false
                            onClick(s)
                        }) {
                            Text(s)
                        }
                    }
                }
            }
        }
    )
}
//--------------------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun BottomAppBar() {
    data class MenuItem(val title: String, val icon: ImageVector)

    fun getMenuList() : List<MenuItem> {
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(MenuItem(Navigations.HOME.name, Icons.Filled.Home))
        menuItems.add(MenuItem(Navigations.SEARCH.name, Icons.Filled.Search))
        menuItems.add(MenuItem(Navigations.FAVORITE.name, Icons.Filled.Favorite))
        menuItems.add(MenuItem(Navigations.SETTINGS.name, Icons.Filled.Settings))
        return menuItems
    }

    val bottomMenuList = getMenuList()

    BottomNavigation {
        bottomMenuList.forEach { bottomMenu ->
            BottomNavigationItem(
                selected = bottomMenu.title == "Home",
                onClick = {
                },
                icon = {
                    Icon(
                        imageVector = bottomMenu.icon,
                        contentDescription = bottomMenu.title
                    )
                })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MySnackHost(state: SnackbarHostState) {
    SnackbarHost(
        state,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Black, RoundedCornerShape(8.dp)),
                action = {
                    Text(
                        text = "HIDE",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                state.currentSnackbarData?.dismiss()
                            },
                        style = androidx.compose.ui.text.TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary,
                            fontSize = 18.sp
                        )
                    )
                }
            ) {
                Text(text = data.message)
            }
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FloatingActionButtonSample(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    FloatingActionButton(onClick = {
        scope.launch {
            when (scaffoldState.snackbarHostState.showSnackbar(
                message = "Jetpack Compose Snackbar",
                actionLabel = "Ok"
            )) {
                SnackbarResult.Dismissed ->
                    Log.d("TAB", "Dismissed")
                SnackbarResult.ActionPerformed ->
                    Log.d("TAB", "Action!")
            }
        }
    }) {
        Text("X")
    }
}
//================================================================
            TopAppBar(
                title = {
                    Text("Рабочий календарь",
                        fontSize = 15.sp ,
                        )
                },

//                .fontWeight = FontWeight.Bold,

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
                    if (currentRoute == Routes.SCHEDULE_ROUTE && BuildConfig.ScheduleRouteEnable) {
                        OverflowTopSchedule()
                    }
//========================================================
                    if (currentRoute == Routes.SCHEDULE01_ROUTE && BuildConfig.Schedule01RouteEnable) {
//                        currentRouteSchedule01=RoutesSchedule01.SCHEDULE01SELNULL
                        ActionMenu(schedule01items, defaultIconSpace = 0)
//                        OverflowTopSchedule500()
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
                    if (currentRoute == Routes.SETTINGS_ROUTE && BuildConfig.SettingsRouteEnable) {
                        ActionMenu(settingitems, defaultIconSpace = 3)
                    }
                    if (currentRoute == Routes.SETTINGAPPMETRICA_ROUTE && BuildConfig.AppMetricaOn && BuildConfig.SettingsRouteEnable) {
                        ActionMenu(settingappmetricaitems, defaultIconSpace = 3)
                    }
//========================================================
                }
            )

//===============================================================
 */
//===============================================================
