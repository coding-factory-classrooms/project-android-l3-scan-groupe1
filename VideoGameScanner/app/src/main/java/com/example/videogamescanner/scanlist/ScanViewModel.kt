package com.example.videogamescanner.scanlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Scan(val id: Int, val gtin: String)

class ScanViewModel: ViewModel() {

    private val scan = MutableLiveData<Scan>()

    fun getScan() : LiveData<Scan> = scan

    fun loadScan(scanId: Int) {
        scan.value = Scan(scanId, "012345678905")

    }

}