package com.easy.todolist.android.feature.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.R

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onEvent: (SignUpEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null)
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = uiState.email,
            onValueChange = {
                onEvent(SignUpEvent.OnEmailChanged(it))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.sign_in_up_email))
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = uiState.fullName,
            onValueChange = {
                onEvent(SignUpEvent.OnFullNameChanged(it))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.sign_in_up_full_name))
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = uiState.password,
            onValueChange = {
                onEvent(SignUpEvent.OnPasswordChanged(it))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.sign_in_up_password))
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = uiState.confirmPassword,
            onValueChange = {
                onEvent(SignUpEvent.OnConfirmPasswordChanged(it))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.sign_in_up_confirm_password))
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            enabled = uiState.isActive(),
            onClick = {
                onEvent(SignUpEvent.OnSignUp)
            }
        ) {
            Text(text = stringResource(id = R.string.sign_in_up_up).uppercase())
        }
        val annotatedString = buildAnnotatedString {
            append(stringResource(id = R.string.sign_in_up_do_not_have_account))
            append(" ")
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(stringResource(id = R.string.sign_in_up_in))
            }
        }
        ClickableText(
            modifier = Modifier.fillMaxWidth(),
            text = annotatedString,
            style = TextStyle(textAlign = TextAlign.Center),
            onClick = {
                if (it >= 17) {
                    onEvent(SignUpEvent.SignInClicked)
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}
