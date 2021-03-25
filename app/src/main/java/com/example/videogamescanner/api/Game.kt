package com.example.videogamescanner.api

import com.example.videogamescanner.Game
import com.squareup.moshi.JsonClass
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


@JsonClass(generateAdapter = true)
data class ApiGame(val product : ApiProduct)
@JsonClass(generateAdapter = true)
data class ApiProduct(val title: String, val variants: List<ApiVariant>)
@JsonClass(generateAdapter = true)
data class ApiVariant(val dimensions: List<ApiDimension>)
@JsonClass(generateAdapter = true)
data class ApiDimension(val value: String)



fun mapApiGame(apiGame: ApiGame): Game{
    return Game(
        name = apiGame.product.title,
        coverUrl = "",
        gtin = "",
        release = "",
        type = "",
        plateform = "",
    )

}


interface GameService {
    @GET("/request?")
    fun listGame(@Query("api_key") api_key: String,@Query("type") type: String,@Query("amazon_domain") amazon_domain: String,@Query("gtin") gtin: String ): Call<ApiGame>

}
