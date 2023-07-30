package com.easy.todolist.android.feature.add_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easy.todolist.model.Task

@Composable
fun AddEditTaskSheet(
    task: Task,
    onEvent: (AddEditTaskEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.secondary
            )
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = task.title,
            onValueChange = {
                onEvent(AddEditTaskEvent.OnTitleChanged(it))
            },
            placeholder = {
                Text(text = "Title")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = task.description,
            onValueChange = {
                onEvent(AddEditTaskEvent.OnDescriptionChanged(it))
            },
            placeholder = {
                Text(text = "Description")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = task.deadline.toString(),
            onValueChange = {
                onEvent(AddEditTaskEvent.OnDeadlineChanged(it))
            },
            readOnly = true,
            placeholder = {
                Text(text = "Deadline (Optional)")
            },
            leadingIcon = {
                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.EditCalendar, contentDescription = null)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = task.deadline.toString(),
            onValueChange = {
                onEvent(AddEditTaskEvent.OnAttachmentChanged(it))
            },
            readOnly = true,
            placeholder = {
                Text(text = "Add Image (Optional)")
            },
            leadingIcon = {
                IconButton(onClick = {

                }) {
                    Icon(imageVector = Icons.Default.BrowseGallery, contentDescription = null)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            onClick = { onEvent(AddEditTaskEvent.OnSave) }) {

        }
    }
}