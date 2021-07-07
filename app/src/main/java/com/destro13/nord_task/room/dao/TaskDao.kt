package com.destro13.nord_task.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.destro13.nord_task.room.entity.TaskEntity

@Dao
interface TaskDao {
	@Query("SELECT * FROM tasks")
	fun getTasks(): List<TaskEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addTask(task: TaskEntity)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addTasks(tasks: List<TaskEntity>)

	@Query("SELECT * FROM tasks WHERE id = :taskId")
	fun getTaskById(taskId: Long): TaskEntity?
}