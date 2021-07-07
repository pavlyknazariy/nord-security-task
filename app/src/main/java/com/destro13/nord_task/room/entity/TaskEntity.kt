package com.destro13.nord_task.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.destro13.nord_task.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Long,
    val userId: Int,
    val title: String,
    val updatedAt: String,
    val createdAt: String,
    val completed: Boolean
)

fun Task.toTaskEntity(): TaskEntity = TaskEntity(
    id = taskId.toLong(),
    userId = userId,
    title = title,
    completed = completed,
    updatedAt = updatedAt,
    createdAt = createdAt
)