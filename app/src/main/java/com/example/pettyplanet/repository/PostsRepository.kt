package com.example.pettyplanet.repository

import com.example.pettyplanet.models.SavedPosts

interface PostsRepository {

        suspend fun getPosts(): List<SavedPosts>

        suspend fun insertPost(post: SavedPosts)

        suspend fun deletePost(post: SavedPosts)
}