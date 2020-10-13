package com.example.playerapp.ui.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playerapp.R
import com.example.playerapp.models.Song
import com.example.playerapp.ui.player.adapter.PlayerAdapter
import kotlinx.android.synthetic.main.activity_player.*
import org.koin.android.ext.android.inject

class PlayerActivity : AppCompatActivity() {

    private val viewModel by inject<PlayerViewModel>()
    private lateinit var list: MutableList<Song>
    private lateinit var adapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        getCover()
        setupRecycler()
    }

    private fun setupRecycler() {
        list = mutableListOf()
        adapter = PlayerAdapter(list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PlayerActivity)
            adapter = this@PlayerActivity.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun getCover() {
        viewModel.cover.observe(this, {
            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}