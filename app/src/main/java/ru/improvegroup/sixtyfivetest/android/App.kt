package ru.improvegroup.sixtyfivetest.android

import android.app.Application
import ru.improvegroup.sixtyfivetest.api.fake.FakeRemoteGateway
import ru.improvegroup.sixtyfivetest.di.Scopes
import ru.improvegroup.sixtyfivetest.domain.gateway.RemoteGateway
import toothpick.Toothpick
import toothpick.config.Module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        Toothpick.openScope(Scopes.APP).installModules(Module().apply {
            bind(RemoteGateway::class.java).toInstance(FakeRemoteGateway)
        })
    }
}