package com.example.pettyplanet.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "posts"
)
data class SavedPosts(

    val text: String,
    val createdBy: String,
    val createdAt: Long,
    val ImageURL: String,
    @PrimaryKey
    val id: Long

)