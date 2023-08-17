package com.easy.todolist.android.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataTimePicker(
    modifier: Modifier = Modifier,
    onDateTimeSelected: (Long) -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DatePicker(state = datePickerState)
        TimePicker(state = timePickerState)
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                datePickerState.selectedDateMillis?.let {
                    val timestamp = it + (timePickerState.hour * 3600 + timePickerState.minute * 60) * 1000
                    onDateTimeSelected(timestamp)
                }
            }) {
            Text(text = "CONFIRM")
        }
    }
}