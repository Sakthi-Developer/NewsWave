package com.sakthi.newswave.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.sakthi.newswave.R
import com.sakthi.newswave.domain.model.News

@Composable
fun RecommendedNews(modifier: Modifier = Modifier,
                    news: News
) {

    Spacer(modifier = modifier
        .height(16.dp)
        .fillMaxWidth())

    Row (
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        SubcomposeAsyncImage(
            model = news.imageUrl.trim(),
            loading = {
                CircularProgressIndicator(
                    color = Color.Gray,
                    modifier = modifier.align(Alignment.Center).size(20.dp)
                )
            },
            modifier = modifier
                .fillMaxWidth(0.35f)
                .clip(RoundedCornerShape(10.dp)),
            error = { Image(
                painter = painterResource(id = R.drawable.noimage),
                contentDescription = null
            ) },
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Spacer(modifier = modifier
            .height(100.dp)
            .width(8.dp))

        Column (
            modifier = Modifier.fillMaxHeight().padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ){

            Text(text = news.title.trim(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
                    .weight(1f)
            )


            Text(text = "Source: ${news.source.trim()}",
                color = Color.Gray, fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
            )

        }


    }
}