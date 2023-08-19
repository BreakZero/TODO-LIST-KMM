package com.easy.todolist.android.feature.detail

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.common.DateTimeX
import com.easy.todolist.android.common.ImagePicker
import com.easy.todolist.android.feature.detail.components.DateTimePickerSheet
import com.easy.todolist.android.feature.detail.components.DeleteActionSheet
import com.easy.todolist.android.feature.detail.components.EditTaskSheet
import com.easy.todolist.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    sheetUiState: TaskDetailSheetUiState,
    editTask: Task?,
    imagePicker: ImagePicker,
    onEvent: (TaskDetailEvent) -> Unit
) {
    imagePicker.registerPicker(onImagePicked = {
        onEvent(TaskDetailEvent.OnAttachmentChanged(it))
    })
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent(TaskDetailEvent.PopBack)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            tint = MaterialTheme.colorScheme.secondary,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    Row {
                        IconButton(onClick = {
                            onEvent(TaskDetailEvent.ShowEditSheet)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                        IconButton(onClick = {
                            onEvent(TaskDetailEvent.ShowDeleteActions)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            )
        }
    ) {
        if (uiState is TaskDetailUiState.Success) {
            val task = uiState.task
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp)
                    .padding(it),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineMedium,
                        text = task.title
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                        text = task.description
                    )
                    task.attachment?.let { bytes ->
                        Image(
                            modifier = Modifier
                                .fillMaxWidth(fraction = 0.8F)
                                .padding(top = 8.dp),
                            bitmap = BitmapFactory.decodeByteArray(
                                uiState.task.attachment,
                                0,
                                bytes.size
                            ).asImageBitmap(),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = ""
                        )
                    }
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center),
                    text = "Created at ${DateTimeX.formattedDate(task.createAt)}"
                )
            }
            if (sheetUiState.isEditSheetOpen && editTask != null) {
                EditTaskSheet(task = editTask, onEvent = onEvent)
            }
            if (sheetUiState.isDateTimePickerOpen) {
                DateTimePickerSheet(
                    modifier = Modifier.fillMaxWidth(),
                    initDateMillis = task.deadline,
                    onEvent = onEvent
                )
            }
            if (sheetUiState.isDeleteActionOpen) {
                DeleteActionSheet(onEvent = onEvent)
            }
        }
    }
}