package com.example.playerapp

import android.app.Application
import com.example.playerapp.di.playerModule
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