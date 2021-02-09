package com.geekbrains.nasalibkotlin.view.main

import com.geekbrains.nasalibkotlin.model.entity.Element
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ListView : MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun updateRecyclerView(elements: List<Element?>?)

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showError(msg: Int)
}