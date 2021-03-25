package com.example.videogamescanner.api

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.videogamescanner.R
import com.example.videogamescanner.ScanBarActivity
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
    private val gtin = intent.getStringExtra("result")

    private val LAUNCH_SECOND_ACTIVITY = 1

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create(GameService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)
        Log.i(TAG, "onCreate: ")

        val intent = Intent(this, ScanBarActivity::class.java)
        startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY)

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            // Check that it is the SecondActivity with an OK result
            if (requestCode == LAUNCH_SECOND_ACTIVITY) {
                if (resultCode == Activity.RESULT_OK) {
                    val returnString = data!!.getStringExtra("result")
                    Log.i(TAG, "onActivityResult: Success $returnString")
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    Log.i(TAG, "onActivityResult: fail")
                }
            }
        }


        val gameRequest = service.listGame(apiKey, type, amazon_domain, gtin!!)
        gameRequest.enqueue(object : Callback<ApiGame> {
            override fun onResponse(call: Call<ApiGame>, response: Response<ApiGame>) {
                Log.i(TAG, "onResponse: ")
                val game = response.body()
                if (game != null) {
                    val ApiGameResponse = response.body()!!
                    val game = mapApiGame(ApiGameResponse)
                    Log.i(TAG, "Apigame :  ${ApiGameResponse}")
                    Log.i(TAG, "game: $game")
                }
            }

            override fun onFailure(call: Call<ApiGame>, t: Throwable) {
                Log.i(TAG, "onFailure: $t")
                error("KO")
            }


        })
    }
}