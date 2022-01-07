package com.example.pettyplanet.repository

import androidx.lifecycle.LiveData
import com.example.pettyplanet.daos.SavedPostsDao
import com.example.pettyplanet.models.SavedPosts

class PostRepositoryImpl(
    private val dao: SavedPostsDao
) : PostsRepository {


    override fun getPosts(): LiveData<List<SavedPosts>> {
        return dao.getPosts()
    }

    override suspend fun insertPost(post: SavedPosts) {
        dao.inserPost(post)
    }

    override suspend fun deletePost(post: SavedPosts) {
        dao.deletePost(post)
    }
}