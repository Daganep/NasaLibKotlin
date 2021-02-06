package com.geekbrains.nasalibkotlin.model.retrofit

import com.geekbrains.nasalibkotlin.model.entity.NasaResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("search")
    fun getMedia(@Query("q") q: String?, @Query("page")page: String): Observable<NasaResponse>
}