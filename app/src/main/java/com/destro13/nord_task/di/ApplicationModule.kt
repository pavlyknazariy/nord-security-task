package com.destro13.nord_task.di

import android.content.Context
import androidx.room.Room
import com.destro13.nord_task.api.ToDoApi
import com.destro13.nord_task.api.ToDoApiFactory
import com.destro13.nord_task.room.AppDatabase
import com.destro13.nord_task.room.dao.TaskDao
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

private const val DATABASE_NAME = "app_database"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache = Cache(
        context.cacheDir,
        10 * 1024 * 1024
    )

    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .cache(cache)
        .build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideToDoApi(
        gson: Gson,
        httpClient: OkHttpClient
    ): ToDoApi = ToDoApiFactory.create(
        gson,
        httpClient
    ).create(ToDoApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideChatDao(database: AppDatabase): TaskDao = database.tasksDao
}