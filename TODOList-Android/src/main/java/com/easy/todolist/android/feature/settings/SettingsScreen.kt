package com.easy.todolist.android.feature.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.R
import com.easy.todolist.model.User
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    uiState: ProfileUiState,
    onEvent: (SettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Icon(
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.ic_todo_list),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
        uiState.user?.let { user ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_rafiki),
                    contentDescription = null
                )
            }
            ProfileInformation(
                modifier = Modifier.height(48.dp),
                label = "Full Name",
                value = user.fullName
            )
            ProfileInformation(
                modifier = Modifier.height(48.dp),
                label = "Email",
                value = user.email
            )
            ProfileInformation(
                modifier = Modifier.height(48.dp),
                label = "Password",
                value = "******"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    onEvent(SettingsEvent.Logout)
                }
            ) {
                Text("LOG OUT")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun ProfileInformation(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.bodyMedium,
            text = label
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, heightDp = 56)
@Composable
fun ProfileInformationPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ProfileInformation(label = "Email", value = "dekajdkl@qq.com")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SettingsPreview() {
    SettingsScreen(
        uiState = ProfileUiState(
            User(
                UUID.randomUUID().toString(), "Dougie Lu",
                "527916577@qq.com", 120000
            )
        )
    ) {

    }
}