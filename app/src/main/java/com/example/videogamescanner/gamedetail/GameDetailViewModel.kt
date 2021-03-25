package com.example.videogamescanner.gamedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Detail(val id: Int, val title: String)
class GameDetailViewModel : ViewModel(){

    private val detail = MutableLiveData<Detail>()

    fun getDetail() : LiveData<Detail> = detail

    fun loadDetail(detailId: Int){
        detail.value = Detail(detailId,"spidey")
    }

}