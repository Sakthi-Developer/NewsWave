package com.sakthi.newswave.domain.model

data class News(
    val title: String,
    val description: String,
    val imageUrl: String,
    val source: String,
    val publishedAt: String,
    val content: String
)
