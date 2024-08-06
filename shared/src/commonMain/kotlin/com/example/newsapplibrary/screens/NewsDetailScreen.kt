package com.example.newsapplibrary.screens

import ApiService.fetchNewsItemById
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.launch
import com.example.newsapplibrary.model.ListingItemResponse

@Composable
fun NewsDetailScreen(newsId: Int,navController: NavController) {
    var newsItem by remember { mutableStateOf<ListingItemResponse?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val painter = newsItem?.let { rememberImagePainter(it.imageUrl) }

    LaunchedEffect(newsId) {
        coroutineScope.launch {
            val item = fetchNewsItemById(newsId)
            newsItem = item
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        newsItem?.let { item ->
            Column(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .clickable { navController.navigateUp() }
                            .padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = item.title, style = MaterialTheme.typography.headlineSmall)
                if (painter != null) {
                    Image(

                        painter = painter,
                        contentDescription = "image",
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))

                    )
                }
                Text(text = item.content, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 8.dp))
            }
        } ?: run {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
