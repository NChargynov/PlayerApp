package com.example.playerapp.network.api

import com.example.playerapp.models.Song
import retrofit2.Call
import retrofit2.http.GET

interface PlayerApi {

    @GET("studio")
    fun getSongs(): Call<MutableList<Song>>
}