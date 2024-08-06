package com.example.newsapplibrary.model

@kotlinx.serialization.Serializable
data class ListingItemRequest(
    val title: String,
    val summary: String,
    val content: String)
