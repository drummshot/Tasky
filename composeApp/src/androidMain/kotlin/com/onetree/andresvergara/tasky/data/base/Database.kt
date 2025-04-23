package com.onetree.andresvergara.tasky.data.base

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<TaskyDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<TaskyDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}