package com.sakthi.newswave.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakthi.newswave.common.NetworkResult
import com.sakthi.newswave.domain.model.News
import com.sakthi.newswave.domain.usecase.GetEveryThing
import com.sakthi.newswave.domain.usecase.GetHeadlines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getEveryThing: GetEveryThing,
    private val getHeadlines: GetHeadlines
) : ViewModel() {

    private val _news = MutableStateFlow<List<News>>(emptyList())
    val headlines = _news.asStateFlow()

    private val _everything = MutableStateFlow<List<News>>(emptyList())
    val otherNews = _everything.asStateFlow()

    init {
        getHeadLineNews("in")
        getEverything("tamil")
    }

    private fun getHeadLineNews(country: String) {
        viewModelScope.launch {
            getHeadlines(country).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _news.value = result.data ?: emptyList()
                        Log.d("TAG", "getHeadLineNews: Success")
                    }

                    is NetworkResult.Error -> {
                        // Handle the error state, maybe show a message to the user
                        // You can also clear the news list or keep it as it is
                        Log.d("TAG", "getHeadLineNews: ${result.message}")
                    }

                    is NetworkResult.Loading -> {
                        // Handle the loading state if necessary
                        // For example, you might want to show a loading indicator
                        Log.d("TAG", "getHeadLineNews: Loading")
                    }

                }

            }
        }
    }

    private fun getEverything(query: String) {
        viewModelScope.launch {
            getEveryThing(query).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _everything.value = result.data?: emptyList()
                        Log.d("TAG", "getEverything: Success")
                    }
                    is NetworkResult.Loading -> {
                    Log.d("TAG", "getEverything: Loading")
                    }
                    is NetworkResult.Error -> {

                        Log.d("TAG", "getEverything: ${result.message}")
                    }

                }

            }
        }
    }
}