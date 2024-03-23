
import java.io.FileInputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = libs.versions.applicationId.get()
//    namespace = "com.childmathematics.android.shiftschedule.schedulecalendar"
    compileSdk = libs.versions.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.applicationId.get()
//        applicationId = "com.childmathematics.android.shiftschedule.schedulecalendar"
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
        release {
            isMinifyEnabled = true // включение/выключение ProGuard
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
        /*
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

         */
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
//        jvmTarget = "17"
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // /////////////
    // UI SUPPORT
    // ////
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)

//    implementation(libs.androidx.appcompatv7)

    implementation(libs.google.accompanist.swiperefresh)
    implementation(libs.google.accompanist.systemuicontroller)

    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)
    // ---------------------------------------------------------------------
    // Compose
    // For Compose runtime by default coroutine runtime already included from ui, foundation, implicitly
    // Not able to get rid of material lib due to we still use these component and not available yet in material3
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.compose.material3:material3-window-size-class")

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    // /////////////
    // DATA SUPPORT
    // ////
    // Room
    implementation(libs.androidx.room.runtime)
    // implementation(libs.androidx.compose.material3.window.size)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.common)

    // /////////////
    // ADS SUPPORT
    // ////

    implementation(libs.google.ads)
    implementation(libs.yandex.mobileads)
//        implementation(libs.yandex.mobmetrica)

    testImplementation(libs.junit.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espressoCore)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
fun getDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return "\"" + LocalDateTime.now().format(formatter) + "\""
}
