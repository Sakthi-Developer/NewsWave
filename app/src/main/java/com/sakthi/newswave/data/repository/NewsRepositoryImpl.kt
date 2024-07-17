package com.sakthi.newswave.data.repository

import com.sakthi.newswave.data.remote.NewsApi
import com.sakthi.newswave.data.remote.dto.NewsResponseDto
import com.sakthi.newswave.domain.model.News
import com.sakthi.newswave.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
): NewsRepository{
    override suspend fun getHeadLines(country: String): NewsResponseDto {
        return newsApi.getHeadLines(country)
    }

    override suspend fun getEverything(query: String): NewsResponseDto {
        return newsApi.getEverything(query)
    }

}