// Top-level build file where you can add configuration options common to all sub-projects/modules.
/*
buildscript {
}
 */
plugins {
    id("com.android.application") version libs.versions.agp apply false
    id("com.android.library") version libs.versions.agp apply false

    id("org.jetbrains.kotlin.android") version libs.versions.jetbrainsKotlin apply false

    id("com.google.devtools.ksp") version libs.versions.googleDevToolsKsp apply false
}
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
