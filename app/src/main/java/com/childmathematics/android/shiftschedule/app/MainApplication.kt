package com.childmathematics.android.shiftschedule.app

import android.app.Application
import com.childmathematics.android.shiftschedule.data.AppContainer
import com.childmathematics.android.shiftschedule.data.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

//public class MainApplication extends Application {
@HiltAndroidApp
class MainApplication : Application()
/*
{
//public class InventoryApplication  extends Application {


     * AppContainer instance used by the rest of classes to obtain dependencies
     * Экземпляр AppContainer, используемый остальными классами для получения зависимостей.

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

*/