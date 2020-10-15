package com.example.playerapp.models
import com.google.gson.annotations.SerializedName

data class Song(
    var artists: String?= null,
    @SerializedName("cover_image")
    var coverImage: String? = null,
    var song: String? = null,
    var url: String? = null
)
