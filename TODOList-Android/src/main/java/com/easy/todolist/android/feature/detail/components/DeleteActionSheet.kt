package com.easy.todolist.android.feature.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.feature.detail.TaskDetailEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteActionSheet(
    modifier: Modifier = Modifier,
    onEvent: (TaskDetailEvent) -> Unit
) {
    ModalBottomSheet(
        modifier = modifier.fillMaxWidth(),
        onDismissRequest = {
            onEvent(TaskDetailEvent.HideDeleteActions)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    onEvent(TaskDetailEvent.OnDelete)
                }
            ) {
                Text(text = "Delete")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    onEvent(TaskDetailEvent.HideDeleteActions)
                }
            ) {
                Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}