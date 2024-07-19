package com.sakthi.newswave.presentation.viewmodel

import ConnectivityObserver
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
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
    private val getHeadlines: GetHeadlines,
    application: Application
) : AndroidViewModel(application) {

    private val _news = MutableStateFlow<List<News>>(emptyList())
    val headlines = _news.asStateFlow()

    private val _everything = MutableStateFlow<List<News>>(emptyList())
    val otherNews = _everything.asStateFlow()

    init {
        viewModelScope.launch {
            observeConnectivity()
        }
        getHeadLineNews("us")
        getEverything("bitcoin")
    }

    private suspend fun observeConnectivity() {
        ConnectivityObserver.observe(getApplication()).collect { isConnected ->
            if (isConnected) {
                getHeadLineNews("us")
                getEverything("bitcoin")
            }
        }
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

    fun getEverything(query: String) {
        _everything.value = emptyList()
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