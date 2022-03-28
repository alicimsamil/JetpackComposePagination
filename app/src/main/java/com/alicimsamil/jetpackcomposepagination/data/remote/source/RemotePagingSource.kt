package com.alicimsamil.jetpackcomposepagination.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alicimsamil.jetpackcomposepagination.data.remote.model.Article
import com.alicimsamil.jetpackcomposepagination.data.remote.service.NewsService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemotePagingSource @Inject constructor(private val newsService: NewsService) :
    PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response =
                newsService.fetchNews(pageNumber = nextPageNumber)
            LoadResult.Page(
                data = response.articles!!,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}