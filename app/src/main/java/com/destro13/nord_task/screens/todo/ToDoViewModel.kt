package com.destro13.nord_task.screens.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.destro13.nord_task.api.Success
import com.destro13.nord_task.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Error
import javax.inject.Inject
import kotlinx.coroutines.launch
import com.destro13.nord_task.coroutine.withIoContext
import com.destro13.nord_task.coroutine.withMainContext
import com.destro13.nord_task.model.Task
import com.destro13.nord_task.model.toTask
import com.destro13.nord_task.util.MutableSingleLiveData
import com.destro13.nord_task.util.NetworkUtil

const val LOAD_TASK_ERROR_MESSAGE = "Can't find such task"
const val LOAD_TASKS_ERROR_MESSAGE = "Can't find any saved tasks"

@HiltViewModel
class ToDoViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {
    private val _toDoListLiveData = MutableSingleLiveData<List<Task>>()
    val toDoListLiveData: LiveData<List<Task>> get() = _toDoListLiveData

    private val _taskDetailsLiveData = MutableSingleLiveData<Task>()
    val taskDetailsLiveData: LiveData<Task> get() = _taskDetailsLiveData

    private val _errorLiveData = MutableSingleLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getToDoList() {
        viewModelScope.launch {
            withIoContext {
                if (NetworkUtil.isInternetAvailable()) {
                    loadTasksFromApi()
                } else {
                    loadTasksFromDatabase()
                }
            }
        }
    }

    private fun loadTasksFromApi() {
        viewModelScope.launch {
            withIoContext {
                when (val result = toDoRepository.getToDoList()) {
                    is Success -> {
                        val tasks = result.data.data.map { dataItem -> dataItem.toTask() }
                        toDoRepository.saveTasksToDatabase(tasks)
                        withMainContext { _toDoListLiveData.value = tasks }
                    }
                    is Error -> {
                        loadTasksFromDatabase()
                        withMainContext { _errorLiveData.value = result.message }
                    }
                }
            }
        }
    }

    private fun loadTasksFromDatabase() {
        viewModelScope.launch {
            withIoContext {
                val tasks = toDoRepository.getTasks()
                if (tasks.isEmpty()) {
                    withMainContext { _errorLiveData.value = LOAD_TASKS_ERROR_MESSAGE }
                } else {
                    withMainContext { _toDoListLiveData.value = tasks }
                }
            }
        }
    }

    fun showTaskDetails(id: Int) {
        viewModelScope.launch {
            withIoContext {
                val task = toDoRepository.getTask(id)
                task?.let {
                    withMainContext { _taskDetailsLiveData.value = it }
                } ?: run {
                    withMainContext { _errorLiveData.value = LOAD_TASK_ERROR_MESSAGE }
                }
            }
        }
    }
}