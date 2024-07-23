package com.sakthi.newswave.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.sakthi.newswave.R
import com.sakthi.newswave.domain.model.News

@Composable
fun ViewNews(modifier: Modifier = Modifier, news: News, navController: NavController) {

    Scaffold {

        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {

                    // Image
                    SubcomposeAsyncImage(
                        model = news.imageUrl,
                        loading = {
                            // You can add a placeholder here if you want
                        },
                        modifier = Modifier.fillMaxSize(),
                        error = {
                            Image(
                                painter = painterResource(id = R.drawable.noimage),
                                contentDescription = null
                            )
                        },
                        contentDescription = null,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )

                    // Back Button

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(14.dp)
                            .size(30.dp)
                            /*.border(
                            border = BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(20.dp)
                        )*/
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = Color.White
                    )



                   /* Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "more",
                        modifier = Modifier
                            .padding(14.dp)
                            .size(30.dp)
                            .align(Alignment.TopEnd)*//*
                                .border(
                                    border = BorderStroke(1.dp, Color.Black),
                                    shape = RoundedCornerShape(20.dp)
                                )*//*,
                        tint = Color.White
                    )*/

                }

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(18.dp)
                ) {
                    Text(
                        text = news.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    )

                    Spacer(
                        modifier = modifier
                            .height(16.dp)
                            .fillMaxWidth()
                    )


                    Text(
                        text = news.content,
                        fontSize = 14.sp,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Spacer(
                        modifier = modifier
                            .height(16.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Source: ${news.source}",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(
                        modifier = modifier
                            .height(16.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Published At: ${news.publishedAt.trim().substring(0, 10)}",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(
                        modifier = modifier
                            .height(16.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Note: This is an free api for developers so the content of the news is restricted ",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                }

            }

        }
    }
}


