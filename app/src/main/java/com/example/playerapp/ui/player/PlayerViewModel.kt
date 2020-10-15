package com.example.playerapp.ui.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.playerapp.base.BaseViewModel
import com.example.playerapp.models.Song
import com.example.playerapp.repository.PlayerRepository

class PlayerViewModel(private val playerRepository: PlayerRepository): BaseViewModel() {

    var cover: MutableLiveData<MutableList<Song>> = MutableLiveData()

    init {
        getSongs()
    }

    private fun getSongs(){
        cover = playerRepository.getSongs()
        loading.value = false
    }
}