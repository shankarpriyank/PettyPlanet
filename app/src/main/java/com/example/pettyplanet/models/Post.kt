package com.example.pettyplanet.models

import android.net.Uri

data class Post (

    val text: String = "",
    val createdBy: User = User(),
    val createdAt: Long = 0L,
    val ImageURL : String = ""
)