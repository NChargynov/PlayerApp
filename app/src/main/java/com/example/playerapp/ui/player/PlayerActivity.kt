package com.example.playerapp.ui.player

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playerapp.R
import com.example.playerapp.base.BaseActivity
import com.example.playerapp.models.Song
import com.example.playerapp.ui.player.adapter.PlayerAdapter
import com.example.playerapp.ui.song.SongActivity
import kotlinx.android.synthetic.main.activity_player.*
import org.koin.android.ext.android.inject

class PlayerActivity : BaseActivity<PlayerViewModel>(R.layout.activity_player), PlayerAdapter.OnItemClick {

    override val viewModel by inject<PlayerViewModel>()
    private lateinit var list: MutableList<Song>
    private lateinit var adapter: PlayerAdapter

    override fun setUpViews() {
        setupRecycler()
    }

    override fun setUpObservers() {
        getCover()
        loadingData()
    }

    private fun loadingData() {
        viewModel.loading.observe(this, {
            if (it == true) progressBar.visibility = View.GONE
        })
    }

    override fun setUpListeners() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupRecycler() {
        list = mutableListOf()
        adapter = PlayerAdapter(list, this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PlayerActivity)
            adapter = this@PlayerActivity.adapter
            var itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(R.drawable.divider)!!)
            addItemDecoration(itemDecoration)
        }
    }

    private fun getCover() {
        viewModel.cover.observe(this, {
            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                adapter.notifyDataSetChanged()
                viewModel.loading.value = true
            }
        })
    }

    override fun onItemClick(item: Song) {
        SongActivity.instanceActivity(this, item)
    }
}