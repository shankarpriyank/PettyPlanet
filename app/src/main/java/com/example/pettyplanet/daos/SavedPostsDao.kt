package com.example.pettyplanet.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pettyplanet.models.SavedPosts


@Dao
interface SavedPostsDao {

    @Query("SELECT * FROM posts")
    fun getPosts(): LiveData<List<SavedPosts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserPost(post: SavedPosts)

    @Delete
    suspend fun deletePost(post: SavedPosts)
}