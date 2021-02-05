package com.geekbrains.nasalibkotlin.presenter

import android.util.Log
import com.geekbrains.nasalibkotlin.di.App
import com.geekbrains.nasalibkotlin.model.entity.NasaResponse
import com.geekbrains.nasalibkotlin.model.retrofit.RetrofitApi
import com.geekbrains.nasalibkotlin.view.main.ListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class ListPresenter : MvpPresenter<ListView>() {

    init {
        App.appComponent.inject(this)
    }

    @Inject lateinit var retrofitApi: RetrofitApi
    private lateinit var disposable : Disposable
    fun requestFromServer(query: String){
        val single: Observable<NasaResponse> = retrofitApi.requestServer(query)
        disposable = single.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ emitter: NasaResponse? -> viewState.updateRecyclerView(emitter) },
                        { throwable: Throwable -> Log.e("Error", "onError$throwable") })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.isDisposed.let { disposable.dispose() }
    }
}