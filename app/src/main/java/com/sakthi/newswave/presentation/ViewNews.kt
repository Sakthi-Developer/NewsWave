package com.sakthi.newswave.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

            Box (
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ){

                SubcomposeAsyncImage(
                    model = news.imageUrl,
                    loading = {
                        CircularProgressIndicator(
                            color = Color.Gray,
                            modifier = modifier
                                .align(Alignment.Center)
                                .size(50.dp)
                        )
                    },
                    modifier = modifier
                        .fillMaxWidth(),
                    error = {
                        Image(
                            painter = painterResource(id = R.drawable.noimage),
                            contentDescription = null
                        )
                    },
                    contentDescription = null
                )

                Box(
                    modifier = modifier
                        .size(40.dp)
                        .padding(14.dp)
                        .align(Alignment.TopEnd)
                        .border(
                            border = BorderStroke(1.dp, Color.Gray),
                            RoundedCornerShape(20.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "back",
                        modifier = modifier.size(22.dp), tint = Color.Gray
                    )

                }

                Box(
                    modifier = modifier
                        .size(40.dp)
                        .padding(4.dp)
                        .align(Alignment.TopStart)
                        .border(
                            border = BorderStroke(1.dp, Color.Gray),
                            RoundedCornerShape(20.dp)
                        ).clickable {
                            navController.navigateUp()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back",
                        modifier = modifier.size(22.dp), tint = Color.Gray
                    )

                }
            }


                Spacer(modifier = modifier.height(8.dp))

                Text(text = news.title,
                    fontWeight = FontWeight.Bold,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.padding(12.dp)
                )

                Spacer(modifier = modifier.height(14.dp))

                Text(text = news.content,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier.padding(12.dp)
                )



        }
    }
}
