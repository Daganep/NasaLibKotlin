package com.geekbrains.nasalibkotlin.di

import android.app.Application
import com.geekbrains.nasalibkotlin.model.retrofit.RetrofitApi
import com.geekbrains.nasalibkotlin.utils.ImageSetter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideRetrofitApi(): RetrofitApi {
        return RetrofitApi()
    }

    @Singleton
    @Provides
    fun provideImageSetter(): ImageSetter {
        return ImageSetter()
    }
}