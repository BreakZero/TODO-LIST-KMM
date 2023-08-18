package com.easy.todolist.android.feature.detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easy.todolist.android.components.DataTimePicker
import com.easy.todolist.android.feature.detail.TaskDetailEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerSheet(
    modifier: Modifier = Modifier,
    initDateMillis: Long? = null,
    onEvent: (TaskDetailEvent) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = {
            onEvent(TaskDetailEvent.HideDateTimePicker)
        }
    ) {
        DataTimePicker(
            modifier = Modifier.fillMaxWidth(),
            initDateMillis = initDateMillis,
            onDateTimeSelected = {
                onEvent(TaskDetailEvent.OnDeadlineChanged(it))
            }
        )
    }
}