package com.geekbrains.nasalibkotlin.model.retrofit

import com.geekbrains.nasalibkotlin.model.entity.NasaResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    private val baseUrl = "https://images-api.nasa.gov/"
    private lateinit var api : RetrofitService

    public fun requestServer(q: String?): Observable<NasaResponse>{
        val gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
        val gsonConverterFactory = GsonConverterFactory.create(gson)
        api = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(RetrofitService::class.java)
        return api.getMedia(q, "1").subscribeOn(Schedulers.io())
    }
}