package com.example.playerapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel(){
    var loading = MutableLiveData<Boolean>()
}