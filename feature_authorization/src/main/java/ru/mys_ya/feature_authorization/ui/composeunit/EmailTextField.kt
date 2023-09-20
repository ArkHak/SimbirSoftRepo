package ru.mys_ya.feature_authorization.ui.composeunit

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import ru.mys_ya.feature_authorization.R

@Composable
fun EmailTextField(
    email: String,
    modifier: Modifier = Modifier,
    changeEmail: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = email,
        maxLines = 1,
        placeholder = {
            Text(
                modifier = modifier,
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.hint_input_e_mail),
                color = colorResource(id = ru.mys_ya.core.R.color.black_38)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        onValueChange = { changeEmail(it) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = ru.mys_ya.core.R.color.white),
            focusedIndicatorColor = colorResource(id = ru.mys_ya.core.R.color.black_12),
            unfocusedIndicatorColor = colorResource(id = ru.mys_ya.core.R.color.black_12)
        ),
    )
}
