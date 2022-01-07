package com.example.pettyplanet.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.pettyplanet.database.SavedPostsDatabase
import com.example.pettyplanet.repository.PostRepositoryImpl
import com.example.pettyplanet.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    @Singleton
    @Provides
    fun provideUserID(@ApplicationContext context: Context): String {
        val preferences =
            context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        return preferences.getString("text", "")!!


    }

    @Provides
    @Singleton
    fun providePostDatabase(app: Application): SavedPostsDatabase {
        return Room.databaseBuilder(
            app,
            SavedPostsDatabase::class.java,
            SavedPostsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton

    fun providePostRepository(db: SavedPostsDatabase): PostsRepository {
        return PostRepositoryImpl(db.savedPostDao)
    }


}


