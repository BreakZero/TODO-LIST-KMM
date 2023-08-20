package com.easy.todolist.android.feature.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easy.todolist.android.R
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
                Text(text = stringResource(id = R.string.text_delete))
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                        alpha = 0.6f
                    )
                ),
                onClick = {
                    onEvent(TaskDetailEvent.HideDeleteActions)
                }
            ) {
                Text(text = stringResource(id = R.string.text_cancel))
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}