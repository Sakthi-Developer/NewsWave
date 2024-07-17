package com.sakthi.newswave.data.remote

import com.sakthi.newswave.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getHeadLines(@Query("country") country: String): NewsResponseDto

    @GET("/v2/everything")
    suspend fun getEverything(@Query("q") query: String): NewsResponseDto

}