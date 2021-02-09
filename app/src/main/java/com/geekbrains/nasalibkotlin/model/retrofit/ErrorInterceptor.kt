package com.geekbrains.nasalibkotlin.model.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // before request
        val request = chain.request()
        // execute request
        val response = chain.proceed(request)
        // after request
        // inspect status codes of unsuccessful responses
        return response
    }
}