package com.easy.todolist.android.feature.todo_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easy.todolist.android.enum.TaskCategory
import com.easy.todolist.data.task.DefaultTaskRepository
import com.easy.todolist.data.task.TaskValidator
import com.easy.todolist.model.Task
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val taskRepository: DefaultTaskRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TodoListUIState())

    val uiState = combine(_state, taskRepository.loadTasks()) { state, tasks ->
        state.copy(tasks = tasks)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TodoListUIState())

    private val _eventChannel = Channel<TodoListEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    var newTask: Task? by mutableStateOf(null)
        private set

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.AddNewTask -> {
                newTask = Task(
                    id = -1,
                    title = "",
                    description = "",
                    createAt = System.currentTimeMillis(),
                    accentColor = TaskCategory.values().random().color,
                    deadline = System.currentTimeMillis(),
                    attachment = null
                )
                _state.update {
                    it.copy(isAddNewTaskOpen = true)
                }
            }

            is TodoListEvent.CloseAddNewSheet -> {
                _state.update {
                    it.copy(isAddNewTaskOpen = false)
                }
            }

            is TodoListEvent.OnTitleChanged -> {
                newTask = newTask?.copy(title = event.title)
            }

            is TodoListEvent.OnDescriptionChanged -> {
                newTask = newTask?.copy(description = event.description)
            }

            is TodoListEvent.OnDeadlineChanged -> {
                newTask = newTask?.copy(deadline = event.deadline)
                _state.update {
                    it.copy(isDatePickerOpen = false)
                }
            }

            is TodoListEvent.OnAttachmentChanged -> {
                newTask = newTask?.copy(attachment = event.attachment)
            }

            is TodoListEvent.OnSaveNewTask -> {
                newTask?.let {
                    val validationResult = TaskValidator.validateTask(it)
                    val errors = listOfNotNull(
                        validationResult.titleError,
                        validationResult.descriptionError
                    )
                    if (errors.isEmpty()) {
                        viewModelScope.launch {
                            taskRepository.insertTask(it)
                        }
                        _state.update {
                            it.copy(isAddNewTaskOpen = false)
                        }
                    } else {
                        println(errors.toString())
                    }
                }
            }

            is TodoListEvent.ChooseDeadline -> {
                _state.update {
                    it.copy(isDatePickerOpen = true)
                }
            }

            is TodoListEvent.CloseDeadlinePicker -> {
                _state.update {
                    it.copy(isDatePickerOpen = false)
                }
            }

            else -> {
                viewModelScope.launch {
                    _eventChannel.send(event)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("view model cleared?")
    }
}