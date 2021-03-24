package com.example.videogamescanner

import androidx.lifecycle.ViewModel

data class Scan(val id: Int, val gtin: String)

class ScanViewModel: ViewModel() {

    fun loadScan(scanId: Int) {

    }

}