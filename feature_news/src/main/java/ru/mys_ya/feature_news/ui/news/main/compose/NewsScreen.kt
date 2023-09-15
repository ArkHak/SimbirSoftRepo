package ru.mys_ya.feature_news.ui.news.main.compose

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.load
import coil.size.Scale
import ru.mys_ya.core.R.*
import ru.mys_ya.core.utils.correctDateTime
import ru.mys_ya.feature_news.R
import ru.mys_ya.feature_news.ui.news.main.NewsViewModel
import ru.mys_ya.feature_news.ui.news.main.compose.unit.TopAppBar

@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    modifier: Modifier = Modifier,
    clickFilter: () -> Unit,
    clickItem: (Int) -> Unit,
) {
    val newsList by viewModel.newsList.observeAsState(emptyList())
    viewModel.loadNews()

    Scaffold(
        topBar = {
            TopAppBar(modifier, clickFilter = {
                clickFilter.invoke()
            })
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(id = color.light_grey_two))
                .padding(horizontal = 8.dp)
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(newsList) { news ->
                    Spacer(modifier = modifier.height(10.dp))

                    AndroidView(
                        modifier = modifier
                            .clickable {
                                clickItem(news.id)
                            },
                        factory = { context ->
                            LayoutInflater.from(context).inflate(R.layout.item_news, null)
                        },
                        update = { view ->
                            view.findViewById<ImageView>(R.id.picture_news)
                                .load(news.photos.first()) {
                                    crossfade(true)
                                    scale(Scale.FILL)
                                }
                            view.findViewById<TextView>(R.id.title_news).text = news.name
                            view.findViewById<TextView>(R.id.description_news).text =
                                news.description
                            view.findViewById<TextView>(R.id.time_text).text =
                                correctDateTime(news.startDate, news.endDate)
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(28.dp))
                }
            }

        }
    }
}
