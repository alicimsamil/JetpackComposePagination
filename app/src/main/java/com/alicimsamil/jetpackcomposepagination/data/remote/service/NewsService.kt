package com.alicimsamil.jetpackcomposepagination.data.remote.service

import com.alicimsamil.jetpackcomposepagination.data.remote.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything")
    suspend fun fetchNews(
        @Query("q") keyword: String = "us",
        @Query("apiKey") apiKey: String = "Define API Key",
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") pageNumber: Int
    ): NewsModel
}