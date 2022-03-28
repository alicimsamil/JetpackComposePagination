package com.alicimsamil.jetpackcomposepagination.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.alicimsamil.jetpackcomposepagination.R

@Preview
@Composable
fun ListScreen() {
    val viewModel: ListViewModel = hiltViewModel()
    val news = viewModel.news.collectAsLazyPagingItems()

    Scaffold() {
        LazyColumn {
            items(news) { news ->
                NewsRow(
                    listScreenModel = ListScreenModel(
                        news?.title,
                        news?.content,
                        news?.urlToImage
                    )
                )
            }
        }
    }

}

@Composable
fun NewsRow(listScreenModel: ListScreenModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(2.dp, RectangleShape, true)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
    ) {
        Column {
            AsyncImage(
                model = listScreenModel.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally),
                placeholder = painterResource(id = R.drawable.placeholder)
            )
            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )
            listScreenModel.title?.let { title -> Text(text = title, fontSize = 24.sp) }
            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )
            listScreenModel.content?.let { content -> Text(text = content) }
        }
    }
}