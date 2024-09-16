package com.example.gatopedia.service

import retrofit2.Retrofit

class CatApiService (private val retrofit: Retrofit) {
    private val catApi = retrofit.create(CatApi::class.java)


}