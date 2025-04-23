package com.onetree.andresvergara.tasky.app.di

import com.onetree.andresvergara.tasky.data.base.TaskyDatabase
import com.onetree.andresvergara.tasky.data.task.dao.TaskDao
import com.onetree.andresvergara.tasky.data.task.datasource.TaskDataSource
import com.onetree.andresvergara.tasky.data.task.datasource.TaskLocalDataSource
import com.onetree.andresvergara.tasky.data.task.repo.TaskRepoImpl
import com.onetree.andresvergara.tasky.domain.task.TaskRepo
import com.onetree.andresvergara.tasky.domain.task.usecase.CreateTaskUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.CreateTaskUseCaseImpl
import com.onetree.andresvergara.tasky.domain.task.usecase.ListTasksUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.ListsTasksUseCaseImpl
import com.onetree.andresvergara.tasky.presentation.task.viewmodel.TaskViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            databaseModule(),
            provideDAOModule,
            provideDataSourceModule,
            provideRepositoryModule,
            provideUseCaseModule,
            provideViewModelModule,
        )
    }

expect fun databaseModule(): Module

val provideDAOModule = module {
    single<TaskDao> { get<TaskyDatabase>().taskDao() }
}

val provideDataSourceModule = module {
    singleOf(::TaskLocalDataSource).bind(TaskDataSource::class)
}

val provideRepositoryModule = module {
    singleOf(::TaskRepoImpl).bind(TaskRepo::class)
}

val provideUseCaseModule = module {
    singleOf(::CreateTaskUseCaseImpl).bind(CreateTaskUseCase::class)
    singleOf(::ListsTasksUseCaseImpl).bind(ListTasksUseCase::class)
}

val provideViewModelModule = module {
    viewModelOf(::TaskViewModel)
}