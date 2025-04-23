@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.onetree.andresvergara.tasky.data.base

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.onetree.andresvergara.tasky.data.task.TaskEntity
import com.onetree.andresvergara.tasky.data.task.dao.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

const val DATABASE_NAME = "tasky.db"

@Database(
    entities = [TaskEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class TaskyDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<TaskyDatabase> {
    override fun initialize(): TaskyDatabase
}

fun getTaskyDatabase(
    builder: RoomDatabase.Builder<TaskyDatabase>
): TaskyDatabase {
    return builder
        //.addMigrations(MIGRATIONS)
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}