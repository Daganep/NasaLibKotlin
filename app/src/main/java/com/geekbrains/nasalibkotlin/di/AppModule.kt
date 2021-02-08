package com.geekbrains.nasalibkotlin.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.geekbrains.nasalibkotlin.model.database.AppDatabase
import com.geekbrains.nasalibkotlin.model.retrofit.RetrofitApi
import com.geekbrains.nasalibkotlin.utils.ImageSetter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "nasalib_database")
            .fallbackToDestructiveMigration()
            .build()
    }

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

    @Singleton
    @Provides
    fun provideContext(): Context{return application}
}