package com.sakthi.newswave.presentation

import ConnectivityObserver
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.sakthi.newswave.R
import com.sakthi.newswave.domain.model.News
import com.sakthi.newswave.presentation.components.NewsCard
import com.sakthi.newswave.presentation.components.RecommendedNews
import com.sakthi.newswave.presentation.viewmodel.NewsViewModel
import com.valentinilk.shimmer.shimmer


@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: NewsViewModel = hiltViewModel(), navController: NavController) {



    val connectivityStatus =
        ConnectivityObserver.observe(LocalContext.current).collectAsState(initial = false)

    val headlines = viewModel.headlines.collectAsState()
    val everything = viewModel.otherNews.collectAsState()
    val technology = viewModel.technology.collectAsState()
    val finance = viewModel.finance.collectAsState()
    val sports = viewModel.sports.collectAsState()
    val business = viewModel.business.collectAsState()
    val health = viewModel.health.collectAsState()
    val politics = viewModel.politics.collectAsState()
    val science = viewModel.science.collectAsState()

    val selectedTab = viewModel.selectedTab.collectAsState().value

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            if (!connectivityStatus.value) {
                Text(
                    text = "No Internet Connection",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
                // Header
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "News Wave",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier.align(Alignment.CenterVertically)
                        )

                        Box(
                            modifier = modifier
                                .size(40.dp)
                                .border(
                                    border = BorderStroke(1.dp, Color.Gray),
                                    RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.whitenotificationbell),
                                contentDescription = "notification",
                                modifier = modifier
                                    .size(22.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .clickable {
                                        viewModel.makeToast("No notifications")
                                    }
                            )
                        }
                    }
                    Spacer(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )

                    // Tabs
                    LazyRow(
                        modifier = modifier.padding(top = 4.dp, bottom = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(viewModel.tabs) { tab ->
                            Tabs(
                                tab = tab,
                                isSelected = tab == selectedTab,
                                onTabSelected = viewModel::onTabSelected
                            )
                        }
                    }
                }

                HorizontalDivider()


                // Content
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    item {

                        Spacer(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(8.dp)
                        )


                        if (selectedTab == "For You") {

                            Text(
                                text = "Trending News",
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = modifier
                                    .height(8.dp)
                                    .fillMaxWidth()
                            )

                            LazyRow(
                                modifier = modifier.fillMaxHeight(0.3f),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                            ) {

                                if (headlines.value.isEmpty()) {
                                    items(5) { // Show 5 shimmer placeholders
                                        ShimmerPlaceholder(
                                            modifier = Modifier
                                                .width(300.dp)
                                                .height(200.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        )
                                    }
                                } else {
                                    items(headlines.value) { news ->
                                        if (news.title != "[Removed]") {
                                            NewsCard(news = news, modifier = modifier.clickable {
                                                navController.navigate("viewNews?news=${Gson().toJson(news)}")
                                            })

                                        }
                                    }
                                }

                            }

                            Spacer(
                                modifier = modifier
                                    .height(16.dp)
                                    .fillMaxWidth()
                            )

                            Text(
                                text = "For You",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        Spacer(
                            modifier = modifier
                                .height(8.dp)
                                .fillMaxWidth()
                        )

                    }

                    when (selectedTab) {
                        "For You" -> newsListScreen(newsItems = everything.value, navController)
                        "Technology" -> newsListScreen(newsItems = technology.value, navController)
                        "Finance" -> newsListScreen(newsItems = finance.value, navController)
                        "Sports" -> newsListScreen(newsItems = sports.value, navController)
                        "Business" -> newsListScreen(newsItems = business.value, navController)
                        "Health" -> newsListScreen(newsItems = health.value, navController)
                        "Politics" -> newsListScreen(newsItems = politics.value, navController)
                        "Science" -> newsListScreen(newsItems = science.value, navController)
                    }
                }
            }
        }
    }
}


@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    tab: String,
    isSelected: Boolean,
    onTabSelected: (String) -> Unit
) {
    Text(
        text = if (isSelected) "● $tab" else tab,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onTabSelected(tab)
            }
    )

}

@Composable
fun ShimmerPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .shimmer()
            .background(Color.Gray.copy(alpha = 0.3f))
    )
}

fun LazyListScope.newsListScreen(newsItems: List<News>, navController: NavController) {

    if (newsItems.isEmpty()) {
        items(10) {
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp)
            )
        }
    }

    items(newsItems.filter { it.title.trim() != "[Removed]" }.size) {
        RecommendedNews(news = newsItems[it], modifier = Modifier.clickable {
            navController.navigate("viewNews?news=${Gson().toJson(newsItems[it])}")
        }
        )
    }

}

