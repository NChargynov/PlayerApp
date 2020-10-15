package com.example.playerapp.ui.song

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.playerapp.R
import com.example.playerapp.base.BaseActivity
import com.example.playerapp.extension.loadImage
import com.example.playerapp.models.Song
import com.example.playerapp.ui.player.PlayerViewModel
import kotlinx.android.synthetic.main.activity_song.*
import org.koin.android.ext.android.inject
import java.lang.Exception
import java.lang.reflect.Executable

class SongActivity : BaseActivity<SongViewModel>(R.layout.activity_song) {

    override val viewModel by inject<SongViewModel>()
    private lateinit var mediaPlayer: MediaPlayer
    private var totalTime: Int = 0

    override fun setUpViews() {
        setData()
        createMediaPlayer()
        threadData()
    }

    override fun setUpObservers() {
    }

    override fun setUpListeners() {
        volumeSeekBarListener()
        positionBarListener()
        playClickListener()
    }

    private fun setData() {
        tvSong.text = item?.song
        tvArtists.text = item?.artists
        coverImage.loadImage(this, R.drawable.not_foto, item?.coverImage)
    }

    private fun createMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(item?.url)
            prepareAsync()
            setOnPreparedListener {
                mediaPlayer.isLooping = true
                positionBar.max = mediaPlayer.duration
                totalTime = positionBar.max
                mediaPlayer.setVolume(0.5F, 0.5F)
            }
        }
    }

    private fun playClickListener() {
        play.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                play.setImageResource(R.drawable.ic_play)
            } else {
                mediaPlayer.start()
                play.setImageResource(R.drawable.ic_pause)
            }
        }
    }

    private fun positionBarListener() {
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) mediaPlayer.seekTo(progress)
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            })
    }

    private fun volumeSeekBarListener() {
        volumeSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        var volumeNum = progress / 100.0f
                        mediaPlayer.setVolume(volumeNum, volumeNum)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            })
    }

    private fun threadData() {
        Thread {
            while (mediaPlayer != null) {
                try {
                    var msg = Message()
                    msg.what = mediaPlayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    Log.d("ololo", e.message.toString())
                }
            }
        }.start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            val currentPosition = msg.what
            positionBar.progress = currentPosition

            val elapsedTimes = viewModel.createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTimes

            val remainingTime = viewModel.createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"
        }
    }

    companion object {
        private var item: Song? = null
        fun instanceActivity(activity: Activity?, item: Song) {
            this.item = item
            val intent = Intent(activity, SongActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}