package com.example.pettyplanet.repository

import androidx.lifecycle.LiveData
import com.example.pettyplanet.models.SavedPosts

interface PostsRepository {
    fun getPosts(): LiveData<List<SavedPosts>>

    suspend fun insertPost(post: SavedPosts)

    suspend fun deletePost(post: SavedPosts)
}