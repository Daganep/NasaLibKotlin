package com.geekbrains.nasalibkotlin.presenter

import android.util.Log
import com.geekbrains.nasalibkotlin.di.App
import com.geekbrains.nasalibkotlin.model.database.AppDatabase
import com.geekbrains.nasalibkotlin.model.database.ElementDao
import com.geekbrains.nasalibkotlin.model.entity.Element
import com.geekbrains.nasalibkotlin.model.entity.NasaResponse
import com.geekbrains.nasalibkotlin.model.retrofit.ErrorInterceptor
import com.geekbrains.nasalibkotlin.model.retrofit.RetrofitApi
import com.geekbrains.nasalibkotlin.view.main.ListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.*
import javax.inject.Inject

@InjectViewState
class ListPresenter : MvpPresenter<ListView>() {

    @Inject lateinit var retrofitApi: RetrofitApi
    @Inject lateinit var appDatabase: AppDatabase
    private var subscriptions : CompositeDisposable
    private val elementDao: ElementDao?
    private var elements: List<Element?>? = null
    private val TAG: String = "Error"

    init {
        App.appComponent.inject(this)
        elementDao = appDatabase.elementDao()
        subscriptions = CompositeDisposable()
    }

    fun requestFromServer(query: String?){
        val single: Observable<NasaResponse> = retrofitApi.requestServer(query)
        subscriptions.add(
                single.observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ emitter ->
                            elements = itemsToElement(emitter)
                            viewState.updateRecyclerView(elements)
                        },
                                { throwable -> Log.e(TAG, "onError$throwable") })
        )
    }

    private fun itemsToElement(response: NasaResponse?): List<Element> {
        val info: MutableList<Element> = ArrayList<Element>()
        if (response != null) {
            response.collection?.items?.let {
                for (x in response.collection.items) {
                    if (x.links != null) info.add(Element(x))
                }
            }
        }
        return info
    }

    fun requestFromDB(){
        subscriptions.add(
                elementDao!!.getAll()?.subscribeOn(Schedulers.io())!!
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { elements1 ->
                                    elements = elements1
                                    viewState.checkDB(elements1)
                                },
                                { throwable -> Log.e(TAG, "onError$throwable") })
        )
    }

    private fun putToDB(){
        if(elements?.size!! > 0){
            subscriptions.add(
                    elementDao!!.insertList(elements)!!
                            .subscribeOn(Schedulers.io())
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe { throwable -> Log.e(TAG, "onError$throwable") })
        }
    }

    fun saveLastResult(){
        subscriptions.add(
                elementDao!!.deleteAll()!!
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ putToDB() },
                                { throwable -> Log.e(TAG, "onError$throwable") })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if(subscriptions.isDisposed) subscriptions.dispose()
    }
}