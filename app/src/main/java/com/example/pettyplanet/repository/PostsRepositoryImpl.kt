package com.example.pettyplanet.repository

import com.example.pettyplanet.daos.SavedPostsDao
import com.example.pettyplanet.models.SavedPosts

class PostRepositoryImpl(
    private val dao: SavedPostsDao
) : PostsRepository {


    override suspend fun getPosts(): List<SavedPosts> {
        return dao.getPosts()
    }

    override suspend fun insertPost(post: SavedPosts) {
        dao.insertPost(post)
    }

    override suspend fun deletePost(post: SavedPosts) {
        dao.deletePost(post)
    }
}