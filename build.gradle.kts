//import com.android.build.gradle.BaseExtension
//import com.android.build.gradle.internal.dsl.BaseFlavor
//import com.android.build.gradle.internal.dsl.DefaultConfig
//import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
//import io.gitlab.arturbosch.detekt.Detekt
//import java.util.Locale
//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//import org.jlleitschuh.gradle.ktlint.KtlintExtension

//====================================
buildscript {

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }



    dependencies {
        /*
            classpath (libs.android.gradlePlugin)
            classpath (libs.jetbrains.kotlin.gradlePlugin)

            // Add the dependency for the Crashlytics Gradle plugin
            classpath (libs.google.firebase.crashlytics.gradlePlugin)

            classpath (libs.google.firebase.performance.gradlePlugin)

            classpath (libs.google.hilt.gradlePlugin)
            classpath (libs.google.servicesPlugin)

         */
 //           classpath (libs.squareup.wire.gradlePlugin)
    }
}

//======================================



@Suppress(
    "DSL_SCOPE_VIOLATION",
    "MISSING_DEPENDENCY_CLASS",
    "UNRESOLVED_REFERENCE_WRONG_RECEIVER",
    "FUNCTION_CALL_EXPECTED"
)


// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
//    alias(libs.plugins.android.gradle.Plugin)    apply false
//    alias(libs.plugins.jetbrains.kotlin.gradlePlugin)   apply false
//    alias(libs.plugins.google.firebase.crashlytics.gradlePlugin)    apply false
//    alias(libs.plugins.google.firebase.performance.gradlePlugin)    apply false
//    alias(libs.plugins.google.hilt.gradlePlugin)    apply false
//    alias(libs.plugins.google.servicesPlugin)   apply false
//    alias(libs.plugins.squareup.wire.gradlePlugin)  apply false

    alias(libs.plugins.google.firebase.crashlytics.gradle.plugin)    apply false
    alias(libs.plugins.google.firebase.performance.gradle.plugin)    apply false

    alias(libs.plugins.arturbosch.detekt)   apply false
    alias(libs.plugins.jlleitschuh.ktlint.gradle)   apply false
    alias(libs.plugins.benmanes.versions)   apply false


    jacoco

 }

allprojects {
/*
    project.ext {
        minSdkVersion = rootProject.libs.versions.min.sdk.get().toInt()
//        minSdkVersion = 21
        targetSdkVersion = rootProject.libs.versions.target.sdk.get().toInt()
        compileSdkVersion = rootProject.libs.versions.compile.sdk.get().toInt()
        /*
        buildToolsVersion = "33.0.0"
        appId = project.properties["application_id"]

         */
        buildToolsVersion = rootProject.libs.versions.buildToolsVersion.get()
        appId = rootProject.libs.versions.applicationId.get()
//        versionCode = Integer.parseInt(project.properties["version_code"])
//        versionName = project.properties["version_name"]
        versionCode = rootProject.libs.versions.versionCode.get().toInt()
        versionName = rootProject.libs.versions.versionName.get()
    }
*/
}
/*
task clean(type: Delete) {
    delete rootProject.buildDir
}
*/
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
