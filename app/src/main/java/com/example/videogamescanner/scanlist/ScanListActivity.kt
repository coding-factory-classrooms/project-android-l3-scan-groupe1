package com.example.videogamescanner.scanlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videogamescanner.Game
import com.example.videogamescanner.R
import com.example.videogamescanner.databinding.ActivityScanListBinding

private const val TAG = "ScanListActivity"

class ScanListActivity : AppCompatActivity() {

    private val model : ScanViewModel by viewModels()

    private lateinit var binding: ActivityScanListBinding
    private lateinit var adapter: ScanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.getScanLiveData().observe(this, Observer { games -> updateGames(games!!)})

        adapter = ScanAdapter(listOf())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        model.loadScan()

    }

    private fun updateGames(games: List<Game>) {
        adapter.updateDataSet(games)
    }
}