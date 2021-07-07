package com.destro13.nord_task.di

import com.destro13.nord_task.repository.ToDoRepository
import com.destro13.nord_task.repository.ToDoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryBindsModule {

    @Binds
    abstract fun bindToDoRepository(toDoRepository: ToDoRepositoryImpl): ToDoRepository
}