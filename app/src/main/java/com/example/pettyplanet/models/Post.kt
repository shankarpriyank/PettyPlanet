package com.example.pettyplanet.models

data class Post(

    var text: String = "",
    var createdBy: User = User(),
    var createdAt: Long = 0L,
    var ImageURL: String = ""
)