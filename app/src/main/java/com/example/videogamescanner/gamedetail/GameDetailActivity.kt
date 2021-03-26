package com.example.videogamescanner.gamedetail

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.videogamescanner.databinding.ActivityGameDetailBinding
import com.squareup.picasso.Picasso

class GameDetailActivity : AppCompatActivity() {

    private val model: GameDetailViewModel by viewModels()
    private lateinit var  binding: ActivityGameDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate((layoutInflater))
        setContentView(binding.root)

        Picasso.get().load(intent.getStringExtra("coverUrl")).into(binding.detailCoverImageView);

        //val uri: Uri = Uri.parse(intent.getStringExtra("coverUrl"))

        binding.detailTitleTextView.text = intent.getStringExtra("name")
        //binding.detailCoverImageView.setImageURI(uri)
        binding.detailRateTextView.text = intent.getStringExtra("rate")
        binding.detailPublisherTextView.text = intent.getStringExtra("publisher")
        binding.detailReleaseDateTextView.text = intent.getStringExtra("release")

        model.getDetail().observe(this, Observer { detail -> onItemLoad(detail!!) })
        model.loadDetail(12)
    }

    private fun onItemLoad(detail: Detail) {
        val itemTitle =intent.getStringExtra("name")
        val itemCoverUrl =intent.getStringExtra("coverUrl")
        val itemRate =intent.getStringExtra("rate")
        val itemPublisher =intent.getStringExtra("publisher")
        val itemRelease =intent.getStringExtra("release")
    }


}