package com.sakthi.newswave.presentation.viewmodel

import ConnectivityObserver
import android.app.Application
import android.util.Log
import android.widget.Toast
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

    private val _technology = MutableStateFlow<List<News>>(emptyList())
    val technology = _technology.asStateFlow()

    private val _finance = MutableStateFlow<List<News>>(emptyList())
    val finance = _finance.asStateFlow()

    private val _sports = MutableStateFlow<List<News>>(emptyList())
    val sports = _sports.asStateFlow()

    private val _business = MutableStateFlow<List<News>>(emptyList())
    val business = _business.asStateFlow()

    private val _health = MutableStateFlow<List<News>>(emptyList())
    val health = _health.asStateFlow()

    private val _politics = MutableStateFlow<List<News>>(emptyList())
    val politics = _politics.asStateFlow()

    private val _science = MutableStateFlow<List<News>>(emptyList())
    val science = _science.asStateFlow()

    private val _connectivityStatus = MutableStateFlow<List<News>>(emptyList())
    val connectivityStatus = _connectivityStatus.asStateFlow()


    val tabs = listOf(
        "For You",
        "Technology",
        "Finance",
        "Sports",
        "Business",
        "Health",
        "Politics",
        "Science"
    )

    private val _selectedTab = MutableStateFlow(tabs.first())
    val selectedTab = _selectedTab.asStateFlow()

    init {
        viewModelScope.launch {
            observeConnectivity()

        }

    }

    private suspend fun observeConnectivity() {
        ConnectivityObserver.observe(getApplication()).collect { isConnected ->
            if (isConnected) {
                getHeadLineNews("us")
                getEverything("business")
                getTechnology()
                getFinance()
                getSports()
                getBusiness()
                getHealth()
                getPolitics()
            }
        }
    }

    private fun getHeadLineNews(country: String) {
        viewModelScope.launch {
            getHeadlines(country).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {

                        val temp = result.data?: emptyList()
                        _news.value = temp.filter { it.title.trim() != "[Removed]" }
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
                        _everything.value = result.data ?: emptyList()
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

    fun getTechnology() {
        viewModelScope.launch {
            getEveryThing("technology").collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _technology.value = result.data ?: emptyList()
                    }

                    is NetworkResult.Loading -> {
                    }

                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }

    fun getFinance() {
        viewModelScope.launch {
            getEveryThing("finance").collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _finance.value = result.data ?: emptyList()
                    }

                    is NetworkResult.Loading -> {
                    }

                    is NetworkResult.Error -> {
                    }
                }
            }
        }

    }

    fun getSports() {
        viewModelScope.launch {
            getEveryThing("sports").collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _sports.value = result.data ?: emptyList()
                    }

                    is NetworkResult.Loading -> {
                    }

                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }

    fun getBusiness() {
        viewModelScope.launch {
            getEveryThing("business").collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _business.value = result.data ?: emptyList()
                    }

                    is NetworkResult.Loading -> {
                    }

                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }

    fun getHealth() {
        viewModelScope.launch {
            getEveryThing("health").collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _health.value = result.data ?: emptyList()
                    }

                    is NetworkResult.Loading -> {

                    }

                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }

    fun getPolitics() {
        viewModelScope.launch {
            getEveryThing("politics").collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _politics.value = result.data ?: emptyList()
                    }

                    is NetworkResult.Loading -> {
                    }

                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }

    fun getScience() {
        viewModelScope.launch {
            getEveryThing("science").collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _science.value = result.data ?: emptyList()
                    }

                    is NetworkResult.Loading -> {
                    }

                    is NetworkResult.Error -> {
                    }
                }

            }
        }
    }

    fun makeToast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }

    fun onTabSelected(s: String) {
        _selectedTab.value = s
    }
}
