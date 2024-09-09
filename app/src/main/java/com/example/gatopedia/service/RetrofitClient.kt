package com.example.gatopedia.service

import com.example.gatopedia.service.interceptor.ApiKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.thecatapi.com/v1/"
    private const val API_KEY = "live_taVBWkFvLlW0dnlIL9HPNPOnS3MMtx9GMi2SbYr3ycsdbaFcPLKXQLEA255Klgus"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(API_KEY)) // Adiciona o interceptor com a chave de API
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // Converte as respostas para objetos Kotlin
            .build()
    }

    val catApiService: CatApiService by lazy {
        retrofit.create(CatApiService::class.java)
    }
}