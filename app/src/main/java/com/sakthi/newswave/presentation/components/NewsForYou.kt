package com.sakthi.newswave.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
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

        AsyncImage(model = news.imageUrl, contentDescription = "null",
            imageLoader = ImageLoader(LocalContext.current),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth(0.35f)
                .clip(RoundedCornerShape(10.dp)),
            placeholder = androidx.compose.ui.res.painterResource(id = R.drawable.test),
            error = androidx.compose.ui.res.painterResource(id = R.drawable.test)
        )

        Spacer(modifier = modifier
            .height(100.dp)
            .width(8.dp))

        Column (
            modifier = Modifier.fillMaxHeight().padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ){

            Text(text = news.title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
                    .weight(1f)
            )


            Text(text = "Source: ${news.source}",
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