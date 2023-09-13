package ru.mys_ya.feature_authorization.ui.composeunit

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun HyperlinkText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 14.sp,
        color = colorResource(id = ru.mys_ya.core.R.color.leaf),
        style = TextStyle(
            textDecoration = TextDecoration.Underline,
        )
    )
}
