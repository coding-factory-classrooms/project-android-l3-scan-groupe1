package com.example.videogamescanner.scanlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.videogamescanner.Game

private val games = listOf(
        Game("Spider-Man Miles Morals", "", "12/11/2020", "4.8", "PS5 / PS4"),
        Game("GTA V", "","17/09/2013", "5", "All"),
        Game("Spider-Man Miles Morals", "", "12/11/2020", "4.8", "PS5 / PS4"),
        Game("GTA V", "","17/09/2013", "5", "All"),
        Game("Spider-Man Miles Morals", "", "12/11/2020", "4.8", "PS5 / PS4"),
        Game("GTA V", "","17/09/2013", "5", "All"),
        Game("Spider-Man Miles Morals", "", "12/11/2020", "4.8", "PS5 / PS4"),
        Game("GTA V", "","17/09/2013", "5", "All"),
        Game("Spider-Man Miles Morals", "", "12/11/2020", "4.8", "PS5 / PS4"),
        Game("GTA V", "","17/09/2013", "5", "All")
)

class ScanViewModel: ViewModel() {

    private val gameLiveData = MutableLiveData<List<Game>>()

    fun getScanLiveData() : LiveData<List<Game>> = gameLiveData

    fun loadScan() {
        gameLiveData.value = games

    }

}