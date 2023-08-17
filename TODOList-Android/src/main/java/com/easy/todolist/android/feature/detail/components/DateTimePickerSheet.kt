package com.easy.todolist.android.feature.detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easy.todolist.android.components.DataTimePicker
import com.easy.todolist.android.feature.detail.TaskDetailEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerSheet(
    modifier: Modifier = Modifier,
    onEvent: (TaskDetailEvent) -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {
            onEvent(TaskDetailEvent.HideDateTimePicker)
        }
    ) {
        DataTimePicker(
            modifier = Modifier.fillMaxWidth(),
            onDateTimeSelected = {
                onEvent(TaskDetailEvent.OnDeadlineChanged(it))
            }
        )
    }
}