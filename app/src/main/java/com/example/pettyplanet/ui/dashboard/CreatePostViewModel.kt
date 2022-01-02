package com.example.pettyplanet.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.net.Uri.*
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.pettyplanet.FeedActivity
import com.example.pettyplanet.MainActivity
import com.example.pettyplanet.R
import com.google.common.reflect.Reflection.getPackageName
import java.net.URI

class CreatePostViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"

    }





    val text: LiveData<String> = _text










        }













