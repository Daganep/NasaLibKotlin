package com.geekbrains.nasalibkotlin.di

import com.geekbrains.nasalibkotlin.presenter.ListPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainPresenter: ListPresenter?)
}