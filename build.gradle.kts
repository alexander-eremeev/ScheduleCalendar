//import com.gradle.enterprise.android.build.gradle.BaseExtension
//import com.android.NoKts_build.gradle.internal.dsl.BaseFlavor
//import com.android.NoKts_build.gradle.internal.dsl.DefaultConfig
//import com.gradle.BaseExtension
//import com.android.build.gradle.internal.dsl.BaseFlavor
//import com.android.build.gradle.internal.dsl.DefaultConfig

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
//import io.gitlab.arturbosch.detekt.Detekt
import java.util.Locale
import org.jlleitschuh.gradle.ktlint.KtlintExtension

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
 //      classpath (libs.plugins.benmanes.versions)
        classpath("com.github.ben-manes:gradle-versions-plugin:+")

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

    alias (libs.plugins.google.firebase.crashlytics.gradle.plugin)    apply false
    alias (libs.plugins.google.firebase.performance.gradle.plugin)    apply false

    alias (libs.plugins.arturbosch.detekt)   apply false
    alias (libs.plugins.jlleitschuh.ktlint.gradle)   apply false
    alias (libs.plugins.benmanes.versions)


    jacoco

 }

allprojects {

//    defaultConfig {
//    compileSdkVersion
    project.ext {
        /*
//        minSdkVersion = rootProject.libs.versions.min.sdk.get().toInt()
        minSdk(rootProject.libs.versions.min.sdk.get().toInt())
//        minSdkVersion = 21
//        targetSdkVersion = rootProject.libs.versions.target.sdk.get().toInt()
        targetSdk = rootProject.libs.versions.target.sdk.get().toInt()
//.        compileSdkVersion = rootProject.libs.versions.compile.sdk.get().toInt()
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



//        minSdkVersion = 21
        targetSdkVersion = 33
        compileSdkVersion = 33
        buildToolsVersion = "33.0.0"
        appId = project.properties["application_id"]
        versionCode = Integer.parseInt(project.properties["version_code"])
        versionName = project.properties["version_name"]
*/
    }
    /*
    tasks.named("dependencyUpdates").configure {
        // configure the task, for example wrt. resolution strategies
        rejectVersionIf {
            isNonStable(it.candidate.version)
        }
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
//==========================================
// https://github.com/ben-manes/gradle-versions-plugin
/*
tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

fun isNonStable(version: String): Boolean {
//    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase(Locale.getDefault()).contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

 */

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}
tasks.withType<DependencyUpdatesTask> {


    // Example 2: disallow release candidates as upgradable versions from stable versions
    rejectVersionIf {
        candidate.version.isNonStable() && !currentVersion.isNonStable()
    }
/*
    // Example 3: using the full syntax
    resolutionStrategy {
        componentSelection {
            all {
                if (candidate.version.isNonStable() && !currentVersion.isNonStable()) {
                    reject("Release candidate")
                }
            }
        }
    }
*/
    // optional parameters
    checkForGradleUpdate = true
//    outputFormatter = "json"
    outputFormatter ="plain,json,xml,html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}

//-------------------------------------------
