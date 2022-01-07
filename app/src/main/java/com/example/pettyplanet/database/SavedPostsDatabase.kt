package com.example.pettyplanet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pettyplanet.daos.SavedPostsDao
import com.example.pettyplanet.models.SavedPosts


@Database(
    entities = [SavedPosts::class],
    version = 1
)
abstract class SavedPostsDatabase : RoomDatabase() {

    abstract val savedPostDao: SavedPostsDao

    companion object {
        const val DATABASE_NAME = "posts_db"
    }
}