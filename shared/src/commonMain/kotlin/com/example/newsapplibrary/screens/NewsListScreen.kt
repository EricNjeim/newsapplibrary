package com.example.newsapplibrary.screens

import ApiService.getItems
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.launch
import com.example.newsapplibrary.model.ListingItemResponse

@Composable
fun NewsListScreen(navController: NavController) {
    var result by remember { mutableStateOf(listOf<ListingItemResponse>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            result = getItems()
        }
    }

    NewsList(result, navController)
}

@Composable
fun NewsList(newsItems: List<ListingItemResponse>, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Recent News", modifier = Modifier.padding(bottom = 16.dp), style = androidx.compose.material3.MaterialTheme.typography.headlineLarge)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(newsItems) { item ->
                NewsItemCard(item, navController)
            }
        }
    }
}

@Composable
fun NewsItemCard(item: ListingItemResponse, navController: NavController) {
    val painter = rememberImagePainter(item.imageUrl)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("newsDetail/${item.id}") },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title, style = MaterialTheme.typography.h6)
            Text(
                text = item.summary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 8.dp)
            )
            Image(
                painter = painter,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .aspectRatio(1.78f)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

suspend fun getItems(): List<ListingItemResponse> {
    return ApiService.getItems()
}
