package com.example.playerapp.ui.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.playerapp.models.Song
import com.example.playerapp.repository.PlayerRepository

class PlayerViewModel(private val playerRepository: PlayerRepository): ViewModel() {

    var cover: MutableLiveData<MutableList<Song>> = MutableLiveData()

    init {
        getSongs()
    }

    private fun getSongs(){
        cover = playerRepository.getSongs()
    }
}