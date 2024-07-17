package com.sakthi.newswave.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sakthi.newswave.R
import com.sakthi.newswave.presentation.components.NewsCard
import com.sakthi.newswave.presentation.components.RecommendedNews
import com.sakthi.newswave.presentation.viewmodel.NewsViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: NewsViewModel = hiltViewModel()) {

    val tabs = listOf(
        " ● For You",
        "Technology",
        "Finance",
        "Sports",
        "Business",
        "Health",
        "Politics",
        "Science"
    )

    val headlines = viewModel.headlines.collectAsState()
    val everything = viewModel.otherNews.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

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
                            text = "Morning, Alex👋🏽",
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
                            )
                        }
                    }
                    Spacer(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )

                    LazyRow(
                        modifier = modifier.padding(top = 4.dp, bottom = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(tabs) { tab ->
                            Text(
                                text = tab,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                }

                HorizontalDivider()


                // Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    Spacer(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(8.dp)
                    )


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
                        modifier = modifier.fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(headlines.value) { news ->
                            NewsCard(news = news)

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

                    Spacer(
                        modifier = modifier
                            .height(8.dp)
                            .fillMaxWidth()
                    )

                    if (everything.value.isNotEmpty()) {
                        for (i in 0..40) {
                        RecommendedNews(news = everything.value[i])

                            }
                    }
                }
            }
        }
    }
}
