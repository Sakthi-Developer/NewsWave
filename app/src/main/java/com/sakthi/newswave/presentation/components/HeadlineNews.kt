package com.sakthi.newswave.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.sakthi.newswave.R
import com.sakthi.newswave.domain.model.News

@Composable
fun NewsCard(modifier: Modifier = Modifier, news: News) {

    Box(modifier = modifier
        .width(300.dp)
        .height(200.dp)
        .clip(RoundedCornerShape(10.dp))
        .clickable {

        }
    ) {


        SubcomposeAsyncImage(
            model = news.imageUrl,
            loading = {
                CircularProgressIndicator(
                    color = Color.Gray,
                    modifier = modifier.align(Alignment.Center).padding(70.dp)
                )
            },
            error = {
                Log.d("TAG", "NewsCard: ${news.imageUrl}")
                Image(
                painter = painterResource(id = R.drawable.noimage),
                contentDescription = null
            ) },
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize(),
            contentDescription = null
        )

        Text(text = news.title,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Start,
            maxLines = 2,
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomStart)
                .drawBehind {
                    drawRect(
                        color = Color.Black,
                        alpha = 0.5f
                    )
                }
                .padding(start = 12.dp, top = 12.dp, bottom = 10.dp, end = 12.dp)

        )

    }
}
