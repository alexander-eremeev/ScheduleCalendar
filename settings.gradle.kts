//pluginManager.apply (org.gradle.api.Action)

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ScheduleCalendar"
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*
 https://docs.gradle.com/enterprise/get-started/

Gradle Enterprise помогает анализировать время локальной сборки и сборки CI, а также понимать промахи
Build Cache и другие типичные узкие места производительности сборки Android.
*/
plugins {
//    id("com.gradle.enterprise") version "latest.release"
    id("com.gradle.develocity") version "latest.release"
//    id("com.gradle.velocity") version "latest.release"
}

//gradleEnterprise {
develocity {
    // configuration
    buildScan {
//        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfUseUrl = "https://gradle.com/terms-of-service"
//        termsOfServiceAgree = "yes"
        termsOfUseAgree = "yes"
//        publishAlways()
//        publishing()
    }
}
// ----------------------------------------------------------------------------------------------------
//  When enabled, tasks using a shared build service without declaring the requirement via the Task.usesService method
//   will emit a deprecation warning.
//   Если этот параметр включен, задачи, использующие общую службу сборки без объявления требования
//   с помощью метода Task.usesService, будут выдавать предупреждение об устаревании.
// ?   enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
// ----------------------------------------------------------------------------------------------------
// https://docs.gradle.org/7.0/release-notes.html
// Type-safe project accessors  Типобезопасные методы доступа к проектам
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    // /////////////
    // PRODUCT MODULE
    // ////

    "app"
    // ///////////////
    // CORE MODULE
    // ////

    // /////////////
    // TEST MODULE
    // ////
)
