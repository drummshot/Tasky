package com.onetree.andresvergara.tasky.app.di

import com.onetree.andresvergara.tasky.data.base.TaskyDatabase
import com.onetree.andresvergara.tasky.data.base.getDatabaseBuilder
import com.onetree.andresvergara.tasky.data.base.getTaskyDatabase
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun databaseModule(): Module = module {
    single<TaskyDatabase> {
        val builder = getDatabaseBuilder(
            context = get()
        )
        getTaskyDatabase(builder)
    }
}