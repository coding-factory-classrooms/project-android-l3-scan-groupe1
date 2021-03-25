package com.example.videogamescanner.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class Game(val title: String,
           val coverImageUrl: String,
           val platform: String,
           val releaseDate: String,
           val description: String) {
}

interface GameService {
    @GET("/request?")
    fun listGames(@Query("api_key") api_key: String,@Query("type") type: String,@Query("amazon_domain") amazon_domain: String,@Query("gtin") gtin: String ): Call<ResponseBody>
}