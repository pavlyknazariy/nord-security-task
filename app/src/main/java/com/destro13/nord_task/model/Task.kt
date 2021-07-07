package com.destro13.nord_task.model

import android.os.Parcelable
import com.destro13.nord_task.room.entity.TaskEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val taskId: Int,
    val userId: Int,
    val title: String,
    val updatedAt: String,
    val createdAt: String,
    val completed: Boolean
): Parcelable

fun DataItem.toTask(): Task = Task(
    taskId = id,
    userId = userId,
    title = title,
    completed = completed,
    updatedAt = updatedAt,
    createdAt = createdAt
)

fun TaskEntity.toTask(): Task = Task(
    taskId = id.toInt(),
    userId = userId,
    title = title,
    completed = completed,
    updatedAt = updatedAt,
    createdAt = createdAt
)