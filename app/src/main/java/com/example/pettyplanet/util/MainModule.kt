package com.example.pettyplanet.util

import android.content.Context
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


}