package com.example.pettyplanet.daos

import androidx.room.*
import com.example.pettyplanet.models.SavedPosts


@Dao
interface SavedPostsDao {

    @Query("SELECT * FROM posts")
    fun getPosts(): List<SavedPosts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: SavedPosts): Long

    @Delete
    suspend fun deletePost(post: SavedPosts)
}