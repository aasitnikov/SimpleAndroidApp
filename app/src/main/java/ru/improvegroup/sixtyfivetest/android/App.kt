package ru.improvegroup.sixtyfivetest.android

import android.app.Application
import ru.improvegroup.sixtyfivetest.di.Di

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Di.init()
    }
}