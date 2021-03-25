package com.example.videogamescanner.gamedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.videogamescanner.R
import com.example.videogamescanner.databinding.ActivityGameDetailBinding

class GameDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailTitleTextView.text = "Spiderman"
    }
}