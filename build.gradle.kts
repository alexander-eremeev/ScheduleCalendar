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
// 1    alias(libs.plugins.benmanes.versions)
//    alias(libs.plugins.gradle.versions)
// 2    alias(libs.plugins.littlerobots.version.catalog.update)

    // jacoco
}
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
