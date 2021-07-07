package com.destro13.nord_task.repository

import com.destro13.nord_task.api.ToDoApi
import com.destro13.nord_task.api.Result
import com.destro13.nord_task.api.Success
import com.destro13.nord_task.api.Error
import com.destro13.nord_task.model.Task
import com.destro13.nord_task.model.ToDoResponse
import com.destro13.nord_task.model.toTask
import com.destro13.nord_task.room.dao.TaskDao
import com.destro13.nord_task.room.entity.toTaskEntity
import javax.inject.Inject

interface ToDoRepository {
    suspend fun getToDoList(): Result<ToDoResponse>
    suspend fun saveTasksToDatabase(tasks: List<Task>)
    suspend fun getTask(id: Int): Task?
    suspend fun getTasks(): List<Task>
}

class ToDoRepositoryImpl @Inject constructor(
    private var toDoApi: ToDoApi,
    private var taskDao: TaskDao,
): ToDoRepository {

    override suspend fun getToDoList(): Result<ToDoResponse> {
        val response = toDoApi.getToDoList()
        return if (response.isSuccessful) {
            Success(response.body() as ToDoResponse)
        } else {
            Error(Throwable(response.message()))
        }
    }

    override suspend fun saveTasksToDatabase(tasks: List<Task>) {
        taskDao.addTasks(tasks.map { task -> task.toTaskEntity() })
    }

    override suspend fun getTask(id: Int): Task? {
        return taskDao.getTaskById(id.toLong())?.toTask()
    }

    override suspend fun getTasks(): List<Task> {
        return taskDao.getTasks().map { taskEntity -> taskEntity.toTask() }
    }
}