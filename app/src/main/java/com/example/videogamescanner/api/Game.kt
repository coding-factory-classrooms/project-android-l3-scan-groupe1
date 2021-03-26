package com.example.videogamescanner.api

import android.os.Parcelable
import android.util.Log
import com.example.videogamescanner.Game
import com.squareup.moshi.JsonClass
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.Serializable

private const val TAG = "Detail"

@JsonClass(generateAdapter = true)
data class ApiGame(val product : ApiProduct)
@JsonClass(generateAdapter = true)
data class ApiProduct(val title: String, val specifications : List<ApiSpecification>, val rating : String, val brand : String,val main_image: ApiMainImage)
@JsonClass(generateAdapter = true)
data class ApiMainImage(val link: String)
@JsonClass(generateAdapter = true)
data class ApiSpecification(val value: String, val name: String)
@JsonClass(generateAdapter = true)
data class ApiDimension(val value: String)

var releaseDate: String = ""


fun mapApiGame(apiGame: ApiGame): Game{


    apiGame.product.specifications.forEach{
        Log.i(TAG, "specification: $it")
        if(it.name == "Date de sortie "){
            releaseDate = it.value
        }
    }

    return Game(
        name = apiGame.product.title,
        coverUrl = apiGame.product.main_image.link,
        release = releaseDate,
        rate = apiGame.product.rating,
        publisher =apiGame.product.brand
    )

}


interface GameService {
    @GET("/request?")
    fun listGame(@Query("api_key") api_key: String,@Query("type") type: String,@Query("amazon_domain") amazon_domain: String,@Query("gtin") gtin: String ): Call<ApiGame>

}
