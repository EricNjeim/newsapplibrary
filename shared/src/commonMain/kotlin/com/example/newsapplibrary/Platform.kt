package com.example.newsapplibrary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform