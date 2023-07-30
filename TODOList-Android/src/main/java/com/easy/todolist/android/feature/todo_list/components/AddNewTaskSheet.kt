package com.easy.todolist.android.feature.todo_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.feature.todo_list.TodoListEvent
import com.easy.todolist.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTaskSheet(
    modifier: Modifier = Modifier,
    task: Task?,
    onEvent: (TodoListEvent) -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {
        onEvent(TodoListEvent.CloseAddNewSheet)
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = task?.title.orEmpty(),
                onValueChange = {
                    onEvent(TodoListEvent.OnTitleChanged(it))
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
                value = task?.description.orEmpty(),
                onValueChange = {
                    onEvent(TodoListEvent.OnDescriptionChanged(it))
                },
                placeholder = {
                    Text(text = "Description")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = task?.deadline.toString(),
                onValueChange = {
                    onEvent(TodoListEvent.OnDeadlineChanged(it))
                },
                readOnly = true,
                placeholder = {
                    Text(text = "Deadline (Optional)")
                },
                trailingIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(imageVector = Icons.Default.EditCalendar, contentDescription = null)
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = task?.deadline.toString(),
                onValueChange = {
                    onEvent(TodoListEvent.OnAttachmentChanged(null))
                },
                readOnly = true,
                placeholder = {
                    Text(text = "Add Image (Optional)")
                },
                trailingIcon = {
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
                onClick = { onEvent(TodoListEvent.OnSaveNewTask) }) {
                Text(text = "ADD TODO")
            }
        }
    }
}