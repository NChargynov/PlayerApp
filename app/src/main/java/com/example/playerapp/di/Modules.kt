package com.example.playerapp.di

import com.example.playerapp.network.RetrofitClient
import com.example.playerapp.repository.PlayerRepository
import com.example.playerapp.ui.player.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var playerModule = module {
    single { RetrofitClient().provideSongs() }
    factory { PlayerRepository(get()) }
    viewModel { PlayerViewModel(get()) }
}