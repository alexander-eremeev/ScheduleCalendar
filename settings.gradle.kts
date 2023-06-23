rootProject.name = "ScheduleCalendar"

pluginManagement {

    /**
     * The pluginManagement {repositories {...}} block configures the
     * repositories Gradle uses to search or download the Gradle plugins and
     * their transitive dependencies. Gradle pre-configures support for remote
     * repositories such as JCenter, Maven Central, and Ivy. You can also use
     * local repositories or define your own remote repositories. The code below
     * defines the Gradle Plugin Portal, Google's Maven repository,
     * and the Maven Central Repository as the repositories Gradle should use to look for its dependencies.
     * Блок pluginManagement {repositories {...}} настраивает
     * репозитории, которые Gradle использует для поиска или загрузки плагинов Gradle и
     * их транзитивные зависимости. Gradle предварительно настраивает поддержку удаленного доступа.
     * репозитории, такие как JCenter, Maven Central и Ivy. Вы также можете использовать
     * локальные репозитории или определите свои собственные удаленные репозитории. Код ниже
     * определяет портал плагинов Gradle, репозиторий Google Maven,
     * и центральный репозиторий Maven в качестве репозиториев, которые Gradle должен использовать
     * для поиска своих зависимостей.
     */

    repositories {
        gradlePluginPortal()
        google()
//                    mavenCentral()
    }
}

dependencyResolutionManagement {
    /**
     * The dependencyResolutionManagement {repositories {...}}
     * block is where you configure the repositories and dependencies used by
     * all modules in your project, such as libraries that you are using to
     * create your application. However, you should configure module-specific
     * dependencies in each module-level NoKts_build.gradle.kts file. For new projects,
     * Android Studio includes Google's Maven repository and the
     * Maven Central Repository by default,
     * but it does not configure any dependencies (unless you select a
     *
     * ЗависимостьResolutionManagement {репозитории {...}}
     * Блок, где вы настраиваете репозитории и зависимости, используемые
     * все модули в вашем проекте, такие как библиотеки, которые вы используете для
     * создать свое приложение. Тем не менее, вы должны настроить для конкретного модуля
     * зависимости в каждом файле NoKts_build.gradle.kts уровня модуля. Для новых проектов,
     * Android Studio включает в себя репозиторий Google Maven и
     * Центральный репозиторий Maven по умолчанию,
     * но он не настраивает никаких зависимостей (если только вы не выберете
     * шаблон, который требует некоторых).
     *
     */

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
plugins {
    id("com.gradle.enterprise") version "3.13.4"
}

gradleEnterprise {
    // configuration
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}
//----------------------------------------------------------------------------------------------------
//   When enabled, tasks using a shared build service without declaring the requirement via the Task.usesService method will emit a deprecation warning.
//   Если этот параметр включен, задачи, использующие общую службу сборки без объявления требования
//   с помощью метода Task.usesService, будут выдавать предупреждение об устаревании.
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
//----------------------------------------------------------------------------------------------------
// https://docs.gradle.org/7.0/release-notes.html
// Type-safe project accessors  Типобезопасные методы доступа к проектам
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ///////////////
    // PRODUCT MODULE
    //////

    "app"
    /////////////////
    // CORE MODULE
    //////


    ///////////////
    // TEST MODULE
    //////
)
