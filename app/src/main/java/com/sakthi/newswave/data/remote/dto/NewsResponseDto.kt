package com.sakthi.newswave.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sakthi.newswave.domain.model.News

data class NewsResponseDto(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<Article>


)

fun NewsResponseDto.toNewsList(): List<News> {
    return articles.map { it.toNews() }
}

