package ru.mys_ya.feature_authorization.ui.composeunit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ru.mys_ya.feature_authorization.R

@Composable
fun PasswordTextField(
    password: String,
    modifier: Modifier = Modifier,
    changePassword: (String) -> Unit,
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = password,
        maxLines = 1,
        placeholder = {
            Text(
                text = stringResource(id = R.string.hint_input_password),
                color = colorResource(id = ru.mys_ya.core.R.color.black_38)
            )
        },
        onValueChange = { changePassword(it) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = ru.mys_ya.core.R.color.white),
            focusedIndicatorColor = colorResource(id = ru.mys_ya.core.R.color.black_12),
            unfocusedIndicatorColor = colorResource(id = ru.mys_ya.core.R.color.black_12)
        ),
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon = if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordHidden) "Show password" else "Hide password"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        }
    )
}
