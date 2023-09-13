package ru.mys_ya.feature_authorization.ui.composeunit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.mys_ya.core.R.*
import ru.mys_ya.feature_authorization.R

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    backPressed: () -> Unit,
) {
    Box {
        TopAppBar(
            backgroundColor = colorResource(id = color.leaf_light),
            contentColor = Color.White,
            title = { },
            navigationIcon = {
                IconButton(onClick = { backPressed.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.title_authorization)
                    )
                }
            }
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = stringResource(R.string.title_authorization),
            color = Color.White,
            fontFamily = FontFamily(Font(font.officina_sans_extra_bold_scc, FontWeight.ExtraBold)),
            fontSize = 21.sp,
            textAlign = TextAlign.Center,
        )
    }
}
