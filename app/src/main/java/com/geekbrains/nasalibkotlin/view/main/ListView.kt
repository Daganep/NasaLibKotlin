package com.geekbrains.nasalibkotlin.view.main

import com.geekbrains.nasalibkotlin.model.entity.NasaResponse
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ListView : MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun updateRecyclerView(nasaResponse: NasaResponse?)
}