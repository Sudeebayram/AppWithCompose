package com.example.jetpackcomposeegitim

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeegitim.Task
import com.example.jetpackcomposeegitim.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.compose.runtime.State as ComposeState

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val allTasks: Flow<List<Task>> = repository.allTasks
    private val _taskById = mutableStateOf<Task?>(null)
    val taskById: ComposeState<Task?> get() = _taskById

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    suspend fun getTaskById(taskId: Int): Task? {
        _taskById.value = repository.getTaskById(taskId)
        return _taskById.value
    }}


class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}