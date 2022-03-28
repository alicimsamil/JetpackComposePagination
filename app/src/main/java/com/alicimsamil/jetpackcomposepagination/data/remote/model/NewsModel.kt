package com.alicimsamil.jetpackcomposepagination.data.remote.model

data class NewsModel(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)