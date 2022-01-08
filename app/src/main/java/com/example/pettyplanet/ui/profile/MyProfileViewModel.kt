package com.example.pettyplanet.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pettyplanet.models.SavedPosts
import com.example.pettyplanet.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val repo: PostsRepository
) : ViewModel() {

    private val _feed = MutableLiveData<List<SavedPosts>?>()

    val feed: LiveData<List<SavedPosts>?> = _feed


    fun updateFeed() {


        GlobalScope.launch(Dispatchers.IO) {
            _feed.postValue(
                repo.getPosts()


            )

            Log.d("SavedPost", repo.getPosts().toString())


        }
    }

    fun deleteSavedPost(post: SavedPosts) {

        GlobalScope.launch(Dispatchers.IO) {

            repo.deletePost(post)

            Log.d("DeletedSavedPost", post.toString())
            _feed.postValue(repo.getPosts())


        }

    }


}