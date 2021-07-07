package com.destro13.nord_task.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.destro13.nord_task.room.dao.TaskDao
import com.destro13.nord_task.room.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val tasksDao: TaskDao
}