package ru.mys_ya.feature_news.ui.news.main.compose.unit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.mys_ya.core.R
import ru.mys_ya.feature_news.R.*

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    clickFilter: () -> Unit,
) {
    Box {
        TopAppBar(
            backgroundColor = colorResource(id = R.color.leaf_light),
            contentColor = Color.White,
            title = { },
            actions = {
                IconButton(onClick = {
                    clickFilter.invoke()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = stringResource(string.title_filter),
                        tint = Color.White
                    )
                }
            }
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = stringResource(string.title_news),
            color = Color.White,
            fontFamily = FontFamily(
                Font(
                    R.font.officina_sans_extra_bold_scc,
                    FontWeight.ExtraBold
                )
            ),
            fontSize = 21.sp,
            textAlign = TextAlign.Center,
        )
    }
}
