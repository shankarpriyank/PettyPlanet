package com.example.pettyplanet.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Coming Soon"
    }
    val text: LiveData<String> = _text
}