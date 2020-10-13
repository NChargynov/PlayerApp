package com.example.playerapp.extension

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.Placeholder
import com.bumptech.glide.Glide

fun showToast(context: Context, message: String){
     Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
 }

fun ImageView.loadImage(context: Context, placeholder: Int = 0, url: String?){
    Glide.with(context)
        .asBitmap()
        .placeholder(placeholder)
        .load(url)
        .into(this)
}