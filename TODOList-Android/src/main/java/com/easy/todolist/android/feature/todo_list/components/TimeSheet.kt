package com.easy.todolist.android.feature.todo_list.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easy.todolist.android.components.DataTimePicker
import com.easy.todolist.android.feature.todo_list.TodoListEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSheet(
    modifier: Modifier = Modifier,
    onEvent: (TodoListEvent) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = {
            onEvent(TodoListEvent.HideDateTimePicker)
        }
    ) {
        DataTimePicker(
            modifier = modifier,
            onDateTimeSelected = {
                onEvent(TodoListEvent.OnDeadlineChanged(it))
            }
        )
    }
}