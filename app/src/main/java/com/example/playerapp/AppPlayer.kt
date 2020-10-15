package com.example.playerapp

import android.app.Application
import android.net.Uri
import com.example.playerapp.di.playerModule
import com.example.playerapp.models.Song
import com.example.playerapp.ui.song.SongActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppPlayer: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppPlayer)
            modules(playerModule)}
    }
}