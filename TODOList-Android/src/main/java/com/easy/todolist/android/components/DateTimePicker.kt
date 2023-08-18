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
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataTimePicker(
    modifier: Modifier = Modifier,
    initDateMillis: Long? = null,
    onDateTimeSelected: (Long) -> Unit
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initDateMillis)
    val timePickerState = rememberTimePickerState(initialHour = 12)
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
                    val dateTime =
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.of("UTC"))
                            .plusHours(timePickerState.hour.toLong())
                            .plusMinutes(timePickerState.minute.toLong())
                    val epochMilli = dateTime.atZone(ZoneId.systemDefault()).toInstant()
                        .toEpochMilli()
                    onDateTimeSelected(epochMilli)
                }
            }) {
            Text(text = "CONFIRM")
        }
    }
}