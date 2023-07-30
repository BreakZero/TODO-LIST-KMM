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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val taskRepository: DefaultTaskRepository
) : ViewModel() {

    val uiState =
        taskRepository.loadTasks().map<List<Task>, TodoListUIState>(TodoListUIState::Success)
            .onStart {
                emit(TodoListUIState.Loading)
            }.catch {
                emit(TodoListUIState.Error(it.message ?: "Oops, something went wrong!!!"))
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), TodoListUIState.Loading)

    private val _eventChannel = Channel<TodoListEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    var newTask: Task? by mutableStateOf(null)
        private set
    var isAddNewTaskOpen by mutableStateOf(false)
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
                    deadline = System.currentTimeMillis() + 1 * 60 * 60 * 1000,
                    attachment = null
                )
                isAddNewTaskOpen = true
            }

            is TodoListEvent.CloseAddNewSheet -> {
                isAddNewTaskOpen = false
            }

            is TodoListEvent.OnTitleChanged -> {
                newTask = newTask?.copy(title = event.title)
            }
            is TodoListEvent.OnDescriptionChanged -> {
                newTask = newTask?.copy(description = event.description)
            }
            is TodoListEvent.OnDeadlineChanged -> {
                newTask = newTask?.copy(deadline = System.currentTimeMillis())
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
                            isAddNewTaskOpen = false
                        }
                    } else {
                        println(errors.toString())
                    }
                }
            }

            else -> {
                viewModelScope.launch {
                    _eventChannel.send(event)
                }
            }
        }
    }
}