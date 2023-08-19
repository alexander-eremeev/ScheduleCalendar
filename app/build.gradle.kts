//import com.android.build.gradle.internal.api.ApkVariantOutputImpl
//import com.android.build.api.dsl.Packaging
import java.util.Properties
import java.io.FileInputStream
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
//import java.io.ByteArrayOutputStream
//import java.util.*
//import org.jetbrains.kotlin.util.capitalizeDecapitalize.toLowerCaseAsciiOnly

plugins {
    alias (libs.plugins.android.application)            //подключить Yandex AppMetrica SDK через плаги
    alias (libs.plugins.jetbrains.kotlin.android)
    alias (libs.plugins.jetbrains.kotlin.kapt)

//    alias (libs.plugins.squareup.wire.gradle.plugin)
//    id ("com.squareup.wire")
//??    alias (libs.plugins.google.hilt. .gradle.plugin)

//    id ("dagger.hilt.android.plugin")

    // Make sure that you have the Google services Gradle plugin
//    id ("com.google.gms.google-services")
//???    alias (libs.plugins.google. services.plugin)

    // Add the Crashlytics Gradle plugin
//??    alias (libs.plugins.google.firebase.crashlytics.gradle.plugin)
//    id ("com.google.firebase.crashlytics")

    // Add the Performance Monitoring Gradle plugin
//???    alias (libs.plugins.google.firebase.performance.gradle.plugin)
//    id ("com.google.firebase.firebase-perf")          //Performance Monitoring)
}
// NEW

/*
//?        apply from: "$rootDir/gradle/test-report.gradle"

          ext.codeCoverage = [
              enabled      : true,
          fileBlackList: [
          ],
          fileWhiteList: [
              // UI

 */
//              '**/ui/*ViewModel*',
//              '**/ui/*State*',
              // Data
              // '**/data/*Environment*',
          //                '**/foundation/datasource/local/*Read*',
          //                '**/foundation/datasource/local/*Write*',
//              '**/basis/datasource/local/*Read*',
//              '**/basis/datasource/local/*Write*',
              // '**/foundation/datasource/preference/PreferenceManager*',
              // Core
          //                '**/foundation/extension/*',
//              '**/basis/extension/*',
/*
          ]
          ]


        //------------------------------------------------------
*/


android {
//    val apName = "ScheduleCalendar"

//    namespace = rootProject.libs.versions.applicationId.get().toString()
    namespace = rootProject.libs.versions.applicationId.get()

    compileSdk = rootProject.libs.versions.compile.sdk.get().toInt()
    buildToolsVersion = rootProject.libs.versions.buildToolsVersion.get()

    buildFeatures {
        dataBinding = true
        resValues = true
        viewBinding = true

        // Fix compose compile error

        compose = true

        buildConfig = true
    }

    defaultConfig {
        minSdk = rootProject.libs.versions.min.sdk.get().toInt()
        targetSdk = rootProject.libs.versions.target.sdk.get().toInt()
        applicationId = rootProject.libs.versions.applicationId.get()
        versionCode = rootProject.libs.versions.versionCode.get().toInt()
        versionName = rootProject.libs.versions.versionName.get()
        javaCompileOptions {
            annotationProcessorOptions {
                                      arguments += mapOf("room.schemaLocation" to "$projectDir/room-schemas".toString())
            }
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

       buildConfigField("String", "BUILD_TIMESTAMP", getDate())
        buildConfigField("String", "BUILD_Date_Rus", getDate())
        //------------------------------------------------------------------
        //  Статистика и реклама
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        buildConfigField("boolean","YaAdsEnable","false" ) // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
        // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
        buildConfigField("boolean","AdMobEnable","false" )   // Включав не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
        buildConfigField("boolean", "AppMetricaOn", "false")  // Включав не забудь об арр AppYandexMetricaInit.java
        //======================================================================
        buildConfigField("boolean", "HomeRouteEnable", "true") // Включение-отключение модуля
        buildConfigField("boolean", "SettingsRouteEnable", "false") // Включение-отключение модуля
        buildConfigField("boolean", "ToDoRouteEnable", "false") // Включение-отключение модуля
        buildConfigField("boolean", "ScheduleRouteEnable", "false")// Включение-отключение модуля
        buildConfigField("boolean", "Schedule01RouteEnable", "true") // Включение-отключение модуля
        buildConfigField("boolean", "Schedule500RouteEnable", "true") // Включение-отключение модуля
        //======================================================================
        /*SETTINGS_ROUTE
                     // defining the build date
             buildConfigField( "long", "BUILD_DATE", System.currentTimeMillis() + "L")
               */
    }

    signingConfigs {

            // Create a variable called keystorePropertiesFile, and initialize it to your
            // keystore.properties file, in the rootProject folder.
//        val keystorePropertiesFile = rootProject.file("keystore.properties")
        val keystorePropertiesFile = File(rootProject.libs.versions.keystorePropertiesFile.get())

        // Initialize a new Properties() object called keystoreProperties.
            val keystoreProperties = Properties()

            // Load your keystore.properties file into the keystoreProperties object.
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            //-------------------------------------------
           create("release") {
                keyAlias = keystoreProperties["RELEASE_KEY_ALIAS"] as String
                keyPassword = keystoreProperties["RELEASE_KEY_PASSWORD"] as String
                storeFile = file(keystoreProperties["RELEASE_STORE_FILE"] as String)
                storePassword = keystoreProperties["RELEASE_STORE_PASSWORD"] as String
           }
            //----------------------------
    }
//===================================

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true     //включение/выключение ProGuard
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false      //включение/выключение ProGuard
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            applicationIdSuffix = ".debug"
        }
    }
    kotlin {
        jvmToolchain(17)
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
//        allWarningsAsErrors = true
        allWarningsAsErrors = false
    }
     composeOptions {
        kotlinCompilerExtensionVersion = (libs.versions.androidxComposeCompiler.get())
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    packagingOptions.resources.excludes += setOf(
        "META-INF/**",
        "{AL2.0,LGPL2.1}",
        "okhttp3/**",
        "kotlin/**",
        "org/**",
        "**.properties",
        "**.bin",
        "**/*.proto"
    )
    /*
    wire {
        kotlin {
            android = true
        }
    }
    */
}
//_________________________
    dependencies {

        ///////////////
        // UI SUPPORT
        //////
        //implementation(libs.jetbrains.kotlin.coroutines)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.window)
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.lifecycle.runtimeCompose)
        implementation(libs.androidx.lifecycle.viewModelCompose)
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.navigation.compose)
        implementation(libs.androidx.hilt.navigation.compose)
        implementation(libs.lottie.compose)
        implementation(libs.google.android.material)

        // Use for bottom sheet navigation
        implementation(libs.google.accompanist.navigation)

        implementation(libs.google.accompanist.systemuicontroller)

        implementation(libs.google.accompanist.pager)
        implementation(libs.google.accompanist.insets)

        // Startup
        implementation(libs.androidx.startup)
        implementation(libs.androidx.profileinstaller)

        // Compose
        // For Compose runtime by default coroutine runtime already included from ui, foundation, implicitly
        // Not able to get rid of material lib due to we still use these component and not available yet in material3
        // androidx.compose.material.SwipeToDismiss
        // androidx.compose.material.ModalBottomSheetLayout

        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui.tooling.preview.android)
        androidTestImplementation(platform(libs.androidx.compose.bom))

        // import Compose dependencies as usual
        implementation(libs.androidx.compose.material)
        implementation(libs.androidx.compose.material3)   // ??????  error
        implementation(libs.androidx.compose.material.iconsCore)
        implementation(libs.androidx.compose.material.iconsExtended)
        implementation(libs.androidx.compose.foundation)
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.util)
        implementation(libs.androidx.compose.widget)     // glance --> 34 api

        implementation(libs.io.coil.compose)

        ///////////////
        // ADS SUPPORT
        //////

        implementation(libs.google.ads)
        implementation(libs.yandex.mobileads)
//        implementation(libs.yandex.mobmetrica)

        ///////////////
        // DATA SUPPORT
        //////

        // SQL
        implementation(libs.androidx.room.runtime)
        implementation(libs.androidx.room.ktx)
        kapt(libs.androidx.room.compiler)
        //for java based projects
        annotationProcessor(libs.androidx.room.compiler)


        // Key-value
        implementation(libs.androidx.dataStore.core)
        implementation(libs.google.protobuf)

        // Server
        implementation(libs.bundles.networking)


        ///////////////
        // CORE
        //////

        // Concurrent processing
        implementation(libs.jetbrains.kotlin.coroutines)

        // DI
        implementation(libs.google.hilt.android)
        kapt(libs.google.hilt.compiler)

        // Date time
        coreLibraryDesugaring(libs.android.desugarJdkLibs)

        // Analytics
        // Import the BoM for the Firebase platform
        /*
    Используя Firebase Android BoM , ваше приложение всегда будет использовать
    совместимые версии библиотек Firebase Android.
     */
        implementation(platform(libs.google.firebase))
                // When using the BoM, you don't specify versions in Firebase library dependencies
                implementation (libs.google.firebase.analytics)
                implementation (libs.google.firebase.perf)
                implementation (libs.google.firebase.crashlytics)
                ///////////////
                // DEBUGGING SUPPORT
                //////

                //???   implementation( libs.debugging.compose.uiTooling)


                ///////////////
                // UNIT TEST SUPPORT
                //////

                testImplementation (libs.androidx.test.ext.junit)

                testImplementation (libs.robolectric)
                testImplementation (libs.junit.test)
//??                testimplementation (libs.jetbrains.test.coroutines)
                testImplementation (libs.turbine.test)

                implementation (libs.debugging.okhttp.logging)
                implementation (libs.debugging.chucker)
/*
        //==================================
        implementation (libs.squareup.okio)

 */

    }
// Allow references to generated code
kapt {
        correctErrorTypes = true
}
fun getDate(): String {
//    Date date = new Date()
//    String dates = "\""+date.format("dd.MM.yyyy", TimeZone.default)+"\""
        //=======================
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
//        val dates = LocalDateTime.now().format(formatter)
    val dates = "\""+LocalDateTime.now().format(formatter)+"\""
        //--------------------
        return dates
}

