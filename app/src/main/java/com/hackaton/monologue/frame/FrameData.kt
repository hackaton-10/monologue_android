package com.hackaton.monologue.frame

data class FrameInfo(
    val title: String,
    val tags: List<String>,
    val imageUrl: String,
    val maker: String,
    var isLiked: Boolean
)