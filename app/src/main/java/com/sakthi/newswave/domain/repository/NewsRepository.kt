package com.sakthi.newswave.domain.repository

import com.sakthi.newswave.data.remote.NewsApi
import com.sakthi.newswave.data.remote.dto.NewsResponseDto
import com.sakthi.newswave.domain.model.News
import javax.inject.Inject

interface NewsRepository {

    suspend fun getHeadLines(country: String): NewsResponseDto    // -> For get HeadLines News based on country (country -> country code(ISO 3166 first two letters))

    suspend fun getEverything(query: String): NewsResponseDto     // -> For get Everything News based on tag (q -> query)

}