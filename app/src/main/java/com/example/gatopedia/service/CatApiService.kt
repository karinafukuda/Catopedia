package com.example.gatopedia.service

import com.example.gatopedia.data.CatInformation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("images/search")
    fun getCatInformation(
        @Query("limit") limit: Int = 10,
        @Query("breed_ids") breedIds: String? = null,
        @Query("page") page: Int = 0,
        @Query("has_breeds") hasBreeds: Int = 1
    ): Call<List<CatInformation>>

    @GET("breeds/search")
    fun searchBreedsByName(@Query("q") query: String): Call<List<CatInformation.Breed>>

}