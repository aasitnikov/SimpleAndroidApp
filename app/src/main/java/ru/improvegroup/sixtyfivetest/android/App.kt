package ru.improvegroup.sixtyfivetest.android

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import ru.improvegroup.sixtyfivetest.di.Di

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Di.init(this)
    }
}