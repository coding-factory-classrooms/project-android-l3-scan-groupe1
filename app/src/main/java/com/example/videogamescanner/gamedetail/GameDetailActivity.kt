package com.example.videogamescanner.gamedetail

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.videogamescanner.R
import com.example.videogamescanner.databinding.ActivityGameDetailBinding

class GameDetailActivity : AppCompatActivity() {

    private val model: GameDetailViewModel by viewModels()
    private lateinit var  binding: ActivityGameDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate((layoutInflater))
        setContentView(binding.root)

        binding.detailTitleTextView.text = intent.getStringExtra("title")
        binding.detailTypeTextView.text = intent.getStringExtra("rate")
        binding.detailPlatformTextView.text = intent.getStringExtra("publisher")
        binding.detailReleaseDateTextView.text = intent.getStringExtra("release")

        model.getDetail().observe(this, Observer {detail -> onItemLoad(detail!!)})
        model.loadDetail(12)
    }

    private fun onItemLoad(detail: Detail) {
        val itemTitle =intent.getStringExtra("title")
        val itemRate =intent.getStringExtra("rate")
        val itemPublisher =intent.getStringExtra("publisher")
        val itemRelease =intent.getStringExtra("release")
    }


}