package com.geekbrains.nasalibkotlin.di

import android.app.Application


class App : Application() {
    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = generateAppComponent()
    }

    private fun generateAppComponent(): AppComponent{
        return DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}