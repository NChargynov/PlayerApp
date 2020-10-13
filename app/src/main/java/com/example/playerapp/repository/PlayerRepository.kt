package com.example.playerapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.playerapp.models.Song
import com.example.playerapp.network.api.PlayerApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerRepository(private val coverApi: PlayerApi) {

    fun getSongs(): MutableLiveData<MutableList<Song>>{
        val data: MutableLiveData<MutableList<Song>> = MutableLiveData()
        coverApi.getSongs().enqueue(object : Callback<MutableList<Song>>{
            override fun onResponse(
                call: Call<MutableList<Song>>,
                response: Response<MutableList<Song>>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<MutableList<Song>>, t: Throwable) {
                data.value = null
            }

        })
        return data
    }
}