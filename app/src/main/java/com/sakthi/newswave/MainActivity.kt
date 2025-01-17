package com.sakthi.newswave

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.sakthi.newswave.domain.model.News
import com.sakthi.newswave.presentation.HomeScreen
import com.sakthi.newswave.presentation.ViewNews
import com.sakthi.newswave.presentation.viewmodel.NewsViewModel
import com.sakthi.newswave.ui.theme.LocalSpacing
import com.sakthi.newswave.ui.theme.NewsWaveTheme
import com.sakthi.newswave.ui.theme.Spacing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsWaveTheme {

                val viewModel = hiltViewModel<NewsViewModel>()
                val navController = rememberNavController()


                NavHost(navController = navController, startDestination = "home") {

                    composable("home") {
                        HomeScreen(viewModel = viewModel, navController = navController)
                    }

                    composable("viewNews?news={news}",
                        arguments = listOf(
                            navArgument("news") {
                                type = NavType.StringType
                            }
                        )
                        ) {

                        val newsJsonString = it.arguments?.getString("news")
                        val news = Gson().fromJson(newsJsonString, News::class.java)

                        ViewNews(news = news, navController = navController)
                    }

                }

            }
        }
    }
}
