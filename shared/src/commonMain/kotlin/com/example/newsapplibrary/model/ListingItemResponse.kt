package com.example.newsapplibrary.model

import kotlinx.serialization.Serializable

@Serializable
data class ListingItemResponse(
    val id: Int = 1,
    val title: String = "",
    val summary: String = "",
    val content: String = "",
    val imageUrl:String=""
)
