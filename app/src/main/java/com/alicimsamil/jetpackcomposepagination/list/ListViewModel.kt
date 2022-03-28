package com.alicimsamil.jetpackcomposepagination.list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alicimsamil.jetpackcomposepagination.data.remote.model.Article
import com.alicimsamil.jetpackcomposepagination.data.remote.source.RemotePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val remotePagingSource: RemotePagingSource) :
    ViewModel() {
    val news: Flow<PagingData<Article>> = Pager(PagingConfig(pageSize = 20)) {
        remotePagingSource
    }.flow
}