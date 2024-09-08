package com.example.gatopedia.service.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header("x-api-key", apiKey) // add api-key header
            .build()
        return chain.proceed(request)
    }
}