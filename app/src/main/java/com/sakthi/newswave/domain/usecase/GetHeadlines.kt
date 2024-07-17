package com.sakthi.newswave.domain.usecase

import android.util.Log
import com.sakthi.newswave.common.NetworkResult
import com.sakthi.newswave.data.remote.dto.toNews
import com.sakthi.newswave.data.remote.dto.toNewsList
import com.sakthi.newswave.domain.model.News
import com.sakthi.newswave.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetHeadlines @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(country: String): Flow<NetworkResult<List<News>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val news = newsRepository.getHeadLines(country).toNewsList()
            emit(NetworkResult.Success(news))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Couldn't reach server. Check your internet connection."))
        }
        catch (e: Exception) {
            emit(NetworkResult.Error(e.message.toString()))
        }
    }

}