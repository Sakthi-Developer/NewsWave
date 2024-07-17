package com.sakthi.newswave.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sakthi.newswave.domain.model.News

data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)

fun Article.toNews(): News {

    return News(
        title = title?: "no title",
        description = description?: "no description",
        imageUrl = urlToImage?: "no image",
        source = source.name?: "no source",
        publishedAt = publishedAt?: "no date",
        content = content?: "no content"
    )

}