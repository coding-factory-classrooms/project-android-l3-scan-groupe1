package com.example.videogamescanner.api

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.videogamescanner.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val TAG = "ApiResult"

class ApiActivity : AppCompatActivity() {

    private val url = "https://api.rainforestapi.com/"
    private val apiKey = "2AEA87D63E5F4F769324E34176D77771"
    private val type = "product"
    private val amazon_domain = "amazon.fr"
    private val gtin = "5030931068133"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create(GameService::class.java)

    val gameRequest = service.listGames(apiKey, type, amazon_domain, gtin)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        gameRequest.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val game = response.body()
                if (game != null) {
                    val stringResponse = response.body()?.string()
                    Log.i(TAG, "game :  ${stringResponse}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                error("KO")
            }


        })
    }
}