package com.example.playerapp.ui.song

import com.example.playerapp.base.BaseViewModel
import com.example.playerapp.repository.PlayerRepository

class SongViewModel(private val playerRepository: PlayerRepository): BaseViewModel(){

    fun createTimeLabel(currentPosition: Int): String {
        var timeLabel = ""
        var min = currentPosition / 1000 / 60
        var sec = currentPosition / 1000 % 60
        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec
        return timeLabel
    }
}