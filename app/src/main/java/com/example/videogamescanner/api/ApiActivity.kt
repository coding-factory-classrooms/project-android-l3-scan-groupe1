package com.example.videogamescanner.api

import android.app.Activity
import android.content.Intent
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
import java.io.Serializable


private const val TAG = "ApiResult"

class ApiActivity : AppCompatActivity() {

    private val url = "https://api.rainforestapi.com/"
    private val apiKey = "DCEE4B2713054BF489D164527F65EFC8"
    private val type = "product"
    private val amazon_domain = "amazon.fr"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create(GameService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)
        val gtin: String? = intent.getStringExtra("gtin")
        Log.i(TAG, "gtin: $gtin")
        val gameRequest = service.listGame(apiKey, type, amazon_domain, gtin!!)
        Log.i(TAG, "gameRequest : $gameRequest ")
        gameRequest.enqueue(object : Callback<ApiGame> {
            override fun onResponse(call: Call<ApiGame>, response: Response<ApiGame>) {
                Log.i(TAG, "onResponse: ")
                val game = response.body()
                if (game != null) {
                    val ApiGameResponse = response.body()!!
                    val game = mapApiGame(ApiGameResponse)
                    Log.i(TAG, "Apigame :  ${ApiGameResponse}")
                    Log.i(TAG, "game: $game")

                    val intent = Intent()

                    intent.putExtra("name", game.name)
                    intent.putExtra("coverUrl", game.coverUrl)
                    intent.putExtra("release", game.release)
                    intent.putExtra("rate", game.rate)
                    intent.putExtra("publisher", game.publisher)
                    Log.i(TAG, "intent: $intent")
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<ApiGame>, t: Throwable) {
                Log.i(TAG, "onFailure: $t")
                error("KO")
            }
        })

    }

}