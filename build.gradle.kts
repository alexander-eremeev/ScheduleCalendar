//import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false // подключить Yandex AppMetrica SDK через плаги
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

//            alias (libs.plugins.jetbrains.kotlin.kapt)  apply false     //???
    alias(libs.plugins.google.ksp) apply false // google-ksp

    alias(libs.plugins.google.firebase.crashlytics.gradle.plugin) apply false
    alias(libs.plugins.google.firebase.performance.gradle.plugin) apply false

//    alias(libs.plugins.arturbosch.detekt)
    alias(libs.plugins.jlleitschuh.ktlint.gradle)
    alias(libs.plugins.benmanes.versions)
//    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.littlerobots.version.catalog.update)


    jacoco
}

allprojects {

//    defaultConfig {
//    compileSdkVersion
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
tasks.register<Delete>("clean") {
//    delete(rootProject.buildDir)
//    delete(layout.buildDirectory)
    delete(getbuildDirectory())
}
 */
//  ==========================================
// https://github.com/ben-manes/gradle-versions-plugin
/*
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

    // optional parameters
    checkForGradleUpdate = true
//    outputFormatter = "json"
    outputFormatter = "plain,json,xml,html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}
*/


apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")
// -------------------------------------------
