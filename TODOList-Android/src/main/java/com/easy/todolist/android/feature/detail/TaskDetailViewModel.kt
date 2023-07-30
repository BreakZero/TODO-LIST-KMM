package com.easy.todolist.android.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.todolist.android.common.decoder.StringDecoder
import com.easy.todolist.data.task.DefaultTaskRepository
import com.easy.todolist.model.Task
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    savedStateHandle: SavedStateHandle,
    stringDecoder: StringDecoder,
    taskRepository: DefaultTaskRepository
): ViewModel() {
    private val transferArgs: TaskArgs = TaskArgs(savedStateHandle, stringDecoder)

    private val _eventChannel = Channel<TaskDetailEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    val uiState = taskRepository.findTaskById(transferArgs.taskId.toLong()).map<Task, TaskDetailUIState>(TaskDetailUIState::Success)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), TaskDetailUIState.Loading)

    fun onEvent(event: TaskDetailEvent) {
        when(event) {
            is TaskDetailEvent.OnDelete -> {}
            is TaskDetailEvent.OnEdit -> {
                viewModelScope.launch {
                    _eventChannel.send(event)
                }
            }
            is TaskDetailEvent.PopBack -> {
                viewModelScope.launch {
                    _eventChannel.send(event)
                }
            }
        }
    }
}