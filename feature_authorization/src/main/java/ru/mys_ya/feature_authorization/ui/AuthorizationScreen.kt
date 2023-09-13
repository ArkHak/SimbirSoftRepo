package ru.mys_ya.feature_authorization.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mys_ya.feature_authorization.ui.composeunit.EmailTextField
import ru.mys_ya.feature_authorization.ui.composeunit.HyperlinkText
import ru.mys_ya.feature_authorization.ui.composeunit.PasswordTextField
import ru.mys_ya.core.R.*
import ru.mys_ya.feature_authorization.R
import ru.mys_ya.feature_authorization.ui.composeunit.TopAppBar

@Composable
fun AuthorizationScreen(
    authorizationViewModel: AuthorizationViewModel,
    modifier: Modifier = Modifier,
    login: () -> Unit,
    closeApp: () -> Unit,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(modifier, backPressed = {
                closeApp.invoke()
            })
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Spacer(modifier = modifier.height(60.dp))

            Text(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(R.string.sign_in_through_social_networks),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = color.black_70)
            )

            Spacer(modifier = modifier.height(40.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(painter = painterResource(R.drawable.ic_social_vk), contentDescription = "")
                Image(painter = painterResource(R.drawable.ic_social_fb), contentDescription = "")
                Image(painter = painterResource(R.drawable.ic_social_ok), contentDescription = "")
            }

            Spacer(modifier = modifier.height(60.dp))

            Text(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(R.string.login_authorization),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = color.black_70)
            )

            Spacer(modifier = modifier.height(40.dp))

            Text(
                text = stringResource(R.string.email_title),
                color = colorResource(id = color.black_38),
                fontSize = 14.sp
            )

            Spacer(modifier = modifier.height(2.dp))

            EmailTextField(
                email,
                changeEmail = {
                    authorizationViewModel.updateEmailText(it)
                    email = it
                }
            )

            Spacer(modifier = modifier.height(26.dp))

            Text(
                text = stringResource(R.string.password_title),
                color = colorResource(id = color.black_38),
                fontSize = 14.sp
            )

            Spacer(modifier = modifier.height(2.dp))

            PasswordTextField(
                password,
                changePassword = {
                    authorizationViewModel.updatePasswordText(it)
                    password = it
                }
            )

            Spacer(modifier = modifier.height(26.dp))

            Button(
                modifier = modifier
                    .height(44.dp)
                    .fillMaxWidth(),
                enabled = authorizationViewModel.isValid.value == true,
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = color.leaf_light)),
                onClick = {
                    login.invoke()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.login_button_text).uppercase(),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = modifier.height(20.dp))

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HyperlinkText(
                    text = stringResource(id = R.string.forgot_your_password),
                )
                HyperlinkText(
                    text = stringResource(id = R.string.registration),
                )
            }
        }
    }
}
