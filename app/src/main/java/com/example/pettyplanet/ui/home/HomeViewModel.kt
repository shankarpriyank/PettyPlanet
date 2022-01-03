package com.example.pettyplanet.ui.home

import android.media.Image
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pettyplanet.daos.PostDao
import com.example.pettyplanet.models.Post
import com.example.pettyplanet.ui.dashboard.postdao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log

class HomeViewModel : ViewModel() {



    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _feed = MutableLiveData<List<Post>?>()

    val feed : LiveData<List<Post>?> = _feed



       fun updateFeed() {



            postdao= PostDao()
           GlobalScope.launch {
               _feed.postValue(

                   postdao.getAllPosts()


               )

               Log.e("Retrival", postdao.getAllPosts().toString())





           }








    }


}