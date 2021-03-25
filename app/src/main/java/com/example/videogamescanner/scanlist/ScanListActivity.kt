package com.example.videogamescanner.scanlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videogamescanner.Game
import com.example.videogamescanner.R
import com.example.videogamescanner.ScanBarActivity
import com.example.videogamescanner.databinding.ActivityScanListBinding

private const val TAG = "ScanListActivity"

class ScanListActivity : AppCompatActivity() {

    private val model : ScanViewModel by viewModels()

    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    private lateinit var binding: ActivityScanListBinding
    private lateinit var adapter: ScanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.getScanLiveData().observe(this, Observer { games -> updateGames(games!!)})

        adapter = ScanAdapter(listOf())

        binding.scanButton.setOnClickListener {
//            val intent = Intent(this, ScanBarActivity::class.java)
//            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
//
//            fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//                super.onActivityResult(requestCode, resultCode, data)
//                if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
//                    if (resultCode == Activity.RESULT_OK) {
//
//                        // Get String data from Intent
//                        val returnString = data!!.getStringExtra("result")
//                    }
//                }
//            }
            navigateToScanBar()
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        model.loadScan()

    }

    private fun updateGames(games: List<Game>) {
        adapter.updateDataSet(games)
    }

    private fun navigateToScanBar() {
        val intent = Intent(this, ScanBarActivity::class.java)
        startActivity(intent)
    }
}