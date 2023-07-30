package com.easy.todolist.android.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.common.toDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUIState,
    onEvent: (TaskDetailEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent(TaskDetailEvent.PopBack)
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                    }
                },
                actions = {
                    Row {
                        IconButton(onClick = {
                            onEvent(TaskDetailEvent.OnEdit)
                        }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        }
                        IconButton(onClick = {
                            onEvent(TaskDetailEvent.OnDelete)
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        }
                    }
                }
            )
        }
    ) {
        if (uiState is TaskDetailUIState.Success) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(it)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    text = uiState.task.title
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                    text = uiState.task.description
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center),
                    text = "Created at ${uiState.task.createAt.toDate()}"
                )
            }
        }
    }
}