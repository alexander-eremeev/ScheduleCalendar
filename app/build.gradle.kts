
//import com.android.build.api.dsl.Ndk
import androidx.room.gradle.RoomExtension
//import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties

plugins {
    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

    id("androidx.room")                         // Plugin для настройки параметров компилятора Room
}

android {

//    namespace = libs.versions.applicationId.get().toString()
    namespace = "com.childmathematics.android.shiftschedule"
    compileSdk = libs.versions.compile.sdk.get().toInt()

    buildToolsVersion = libs.versions.buildToolsVersion.get()
    ndkVersion = libs.versions.ndkVersion.get()

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        android.buildFeatures.buildConfig = true

        buildConfigField("String", "BUILD_TIMESTAMP", getDate())
        buildConfigField("String", "BUILD_Date_Rus", getDate())
        // ------------------------------------------------------------------
        //  Статистика и реклама
        // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        buildConfigField("boolean", "YaAdsEnable", "false")
        // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
        // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
        buildConfigField("boolean", "AdMobEnable", "false")
        // Включав не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
        buildConfigField("boolean", "AppMetricaOn", "false") // Включав не забудь об арр AppYandexMetricaInit.java
        // ======================================================================
        buildConfigField("boolean", "HomeRouteEnable", "true") // Включение-отключение модуля
        buildConfigField("boolean", "SettingsRouteEnable", "false") // Включение-отключение модуля
        buildConfigField("boolean", "ToDoRouteEnable", "false") // Включение-отключение модуля
        buildConfigField("boolean", "ScheduleRouteEnable", "false") // Включение-отключение модуля
        buildConfigField("boolean", "Schedule01RouteEnable", "true") // Включение-отключение модуля
        buildConfigField("boolean", "Schedule500RouteEnable", "true") // Включение-отключение модуля
        // ======================================================================
        /*SETTINGS_ROUTE
                     // defining the build date
             buildConfigField( "long", "BUILD_DATE", System.currentTimeMillis() + "L")
         */

        signingConfigs {

            // Create a variable called keystorePropertiesFile, and initialize it to your
            // keystore.properties file, in the  folder.
            val keystorePropertiesFile = File(libs.versions.keystorePropertiesFile.get())

            // Initialize a new Properties() object called keystoreProperties.
            val keystoreProperties = Properties()

            // Load your keystore.properties file into the keystoreProperties object.
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            // -------------------------------------------
            create("release") {
                keyAlias = keystoreProperties["RELEASE_KEY_ALIAS"] as String
                keyPassword = keystoreProperties["RELEASE_KEY_PASSWORD"] as String
                storeFile = file(keystoreProperties["RELEASE_STORE_FILE"] as String)
                storePassword = keystoreProperties["RELEASE_STORE_PASSWORD"] as String
            }
            // ----------------------------
        }
    }

    buildTypes {
        create("customDebugType") {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true // включение/выключение ProGuard
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            // Native Development Kit (NDK) — это набор инструментов, позволяющий использовать код C и C++ с Android.
            // Появление предупреждения в Google Play Console означает, что ваше приложение содержит код C/C++.
            // Для отладки собственного кода необходимы собственные символы отладки.
/*
            ndk {
//                debugSymbolLevel ="none"    // "symbol_table"  "full"
            }
 */
        }
        getByName("debug") {

            multiDexEnabled = true
            isDebuggable = true
            isMinifyEnabled = false // включение/выключение ProGuard
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            applicationIdSuffix = ".debug"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }
    /*
    kotlin {
        compilerOptions {
            compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
        }
    }

     */
    buildFeatures {
        compose = true
    }
/*
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
*/
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
//-------------------------------------------------------------
// The feature "context receivers" is experimental and should be enabled explicitly
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
         compilerOptions {
//            freeCompilerArgs.add("-Xcontext-receivers")
             freeCompilerArgs.add("-Xcontext-parameters")
             // - Чтобы выбрать применение аннотации как к параметру value, так и к полю
             freeCompilerArgs.add("-Xannotation-default-target=param-property")
        }
    }
//-------------------------------------------------------------
    /*
                extensions.configure<RoomExtension> {
                // The schemas directory contains a schema file for each version of the Room database.
                // This is required to enable Room auto migrations.
                // В каталоге schemas находится файл схемы для каждой версии базы данных Room.
                // Это необходимо для включения автоматической миграции Room.
                // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
                schemaDirectory("$projectDir/schemas")

     */
/*
    room {      //buildDirectory
        schemaDirectory("$projectDir/schemas")
    }
*/
    }
extensions.configure<RoomExtension> {
    // The schemas directory contains a schema file for each version of the Room database.
    // This is required to enable Room auto migrations.
    // В каталоге schemas находится файл схемы для каждой версии базы данных Room.
    // Это необходимо для включения автоматической миграции Room.
    // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation( libs.dev.chrisbanes.snapper.snapper)    //?????? Snapper в настоящее время устарел,
    // поскольку его функционал заменен на SnapFlingBehavior,
    // доступный в Jetpack Compose 1.3.0.

    // Native Development Kit (NDK) — это набор инструментов, позволяющий использовать код C и C++ с Android.
    // Для отладки собственного кода необходимы собственные символы отладки.
//    implementation(libs.android.tools.build)
     /////////////
    // UI SUPPORT
    // ////
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)

    // Kotlin Coroutines
    implementation(libs.jetbrains.kotlin.coroutines.core)
    implementation(libs.jetbrains.kotlin.coroutines.android)

    // Hilt
    implementation(libs.google.dagger.hilt)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.core.i18n)
    //testImplementation(libs.junit.test)
    ksp(libs.google.dagger.hilt.compiler )

    // Webkit
    implementation(libs.androidx.webkit.webkit)

    implementation(libs.google.accompanist.swiperefresh)
    implementation(libs.google.accompanist.systemuicontroller)

    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)
    //---------------------------------------------
    // for Play In-App Update
    implementation(libs.google.play.appupdate)
    implementation(libs.google.play.appupdate.ktx)
    // ---------------------------------------------------------------------
    // Compose
    // For Compose runtime by default coroutine runtime already included from ui, foundation, implicitly
    // Not able to get rid of material lib due to we still use these component and not available yet in material3
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.window.size)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    // песочница улучшения конфиденциальности пользователей
    implementation(libs.androidx.privacysandbox.tools.core)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    implementation(libs.splashscreen)


    // /////////////
    // DATA SUPPORT
    // ////
    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)            // Kotlin Symbol Processing (KSP)
    implementation(libs.androidx.room.ktx)      //Kotlin Extensions and Coroutines support for Room
    //implementation(libs.androidx.room.common)
    // /////////////
    // ADS SUPPORT
    // ////
    implementation(libs.google.ads)
//    implementation(libs.yandex.mobileads)
//        implementation(libs.yandex.mobmetrica)
//        implementation(libs.yandex.appmetrica)

    // /////////////
    // TEST AND DEBUG SUPPORT
    // ////
    testImplementation(libs.jetbrains.test.coroutines)
        testImplementation(libs.junit.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
//    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
fun getDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return "\"" + LocalDateTime.now().format(formatter) + "\""
}
