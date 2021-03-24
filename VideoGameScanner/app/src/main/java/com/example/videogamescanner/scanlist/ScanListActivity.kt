package com.example.videogamescanner.scanlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.videogamescanner.R

private const val TAG = "ScanListActivity"

class ScanListActivity : AppCompatActivity() {
    
    private val model : ScanViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_list)

        model.getScan().observe(this, Observer { scan -> onScanUpdated(scan!!)})
        model.loadScan(1)
    }

    private fun onScanUpdated(scan: Scan) {
        Log.i(TAG, "onScanUpdated: $scan")
    }
}