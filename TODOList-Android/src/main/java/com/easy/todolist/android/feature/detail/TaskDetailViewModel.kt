package com.easy.todolist.android.feature.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.todolist.android.common.decoder.StringDecoder
import com.easy.todolist.data.task.DefaultTaskRepository
import com.easy.todolist.model.Task
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    savedStateHandle: SavedStateHandle,
    stringDecoder: StringDecoder,
    private val taskRepository: DefaultTaskRepository
) : ViewModel() {
    private val transferArgs: TaskArgs = TaskArgs(savedStateHandle, stringDecoder)

    private val _eventChannel = Channel<TaskDetailEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    private val _sheetUiState = MutableStateFlow(TaskDetailSheetUiState())
    val sheetUiState = _sheetUiState.asStateFlow()
    val uiState = taskRepository.findTaskById(transferArgs.taskId.toLong()).map {
        editTask = it
        TaskDetailUiState.Success(task = it)
    }.stateIn(viewModelScope, SharingStarted.Lazily, TaskDetailUiState.Loading)

    var editTask: Task? by mutableStateOf(null)
        private set

    fun onEvent(event: TaskDetailEvent) {
        when (event) {
            is TaskDetailEvent.OnTitleChanged -> {
                editTask = editTask?.copy(title = event.title)
            }

            is TaskDetailEvent.OnDescriptionChanged -> {
                editTask = editTask?.copy(description = event.description)
            }

            is TaskDetailEvent.OnDeadlineChanged -> {
                editTask = editTask?.copy(deadline = event.deadline)
            }

            is TaskDetailEvent.OnAttachmentChanged -> {
                editTask = editTask?.copy(attachment = event.attachment)
            }

            is TaskDetailEvent.OnDelete -> {}
            is TaskDetailEvent.ShowEditSheet -> {
                _sheetUiState.update {
                    it.copy(isEditSheetOpen = true)
                }
            }

            is TaskDetailEvent.ShowDateTimePicker -> {
                _sheetUiState.update {
                    it.copy(isDateTimePickerOpen = true)
                }
            }

            is TaskDetailEvent.HideEditSheet -> {
                _sheetUiState.update {
                    it.copy(isEditSheetOpen = false)
                }
            }

            is TaskDetailEvent.HideDateTimePicker -> {
                _sheetUiState.update {
                    it.copy(isDateTimePickerOpen = false)
                }
            }
            is TaskDetailEvent.OnConfirmed -> {
                updateTask()
            }
            is TaskDetailEvent.ShowImagePicker, TaskDetailEvent.PopBack -> dispatchEvent(event)
        }
    }

    private fun updateTask() {
        editTask?.let {
            println(it.toString())
            viewModelScope.launch {
                taskRepository.upsertTask(it)
            }
        }
        _sheetUiState.update {
            it.copy(isEditSheetOpen = false)
        }
    }

    private fun dispatchEvent(event: TaskDetailEvent) {
        viewModelScope.launch {
            _eventChannel.send(event)
        }
    }
}