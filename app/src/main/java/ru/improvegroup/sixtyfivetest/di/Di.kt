package ru.improvegroup.sixtyfivetest.di

import android.util.Log
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.improvegroup.sixtyfivetest.api.gateway.ApiRemoteGateway
import ru.improvegroup.sixtyfivetest.api.retrofit.ApiService
import ru.improvegroup.sixtyfivetest.domain.gateway.RemoteGateway
import toothpick.Toothpick
import toothpick.config.Module

object Di {
    fun init() {
        Toothpick.openScope(Scopes.APP).installModules(Module().apply {
            bind(RemoteGateway::class.java)
                .to(ApiRemoteGateway::class.java)
                .singletonInScope()

            bind(ApiService::class.java).toInstance(createApiService())
        })
    }

    private fun createApiService(): ApiService {
        return Retrofit.Builder()
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiService.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            //TODO: replace with timber
            //TODO: remove wtf
            .addInterceptor(HttpLoggingInterceptor { Log.wtf("65apps", it) })
            .build()
    }
}