package com.onetree.andresvergara.tasky.app.di

import com.onetree.andresvergara.tasky.data.base.TaskyDatabase
import com.onetree.andresvergara.tasky.data.task.dao.TaskDao
import com.onetree.andresvergara.tasky.data.task.datasource.TaskDataSource
import com.onetree.andresvergara.tasky.data.task.datasource.TaskLocalDataSource
import com.onetree.andresvergara.tasky.data.task.repo.TaskRepoImpl
import com.onetree.andresvergara.tasky.data.user.dao.UserDao
import com.onetree.andresvergara.tasky.data.user.datasource.UserDataSource
import com.onetree.andresvergara.tasky.data.user.datasource.UserLocalDataSource
import com.onetree.andresvergara.tasky.data.user.repo.UserRepoImpl
import com.onetree.andresvergara.tasky.domain.base.SessionManager
import com.onetree.andresvergara.tasky.domain.task.TaskRepo
import com.onetree.andresvergara.tasky.domain.task.usecase.CreateTaskUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.CreateTaskUseCaseImpl
import com.onetree.andresvergara.tasky.domain.task.usecase.DeleteTasksUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.DeleteTasksUseCaseImpl
import com.onetree.andresvergara.tasky.domain.task.usecase.ListTasksUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.ListsTasksUseCaseImpl
import com.onetree.andresvergara.tasky.domain.task.usecase.MarkCompletedTasksUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.MarkCompletedTasksUseCaseImpl
import com.onetree.andresvergara.tasky.domain.task.usecase.TaskDetailUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.TaskDetailUseCaseImpl
import com.onetree.andresvergara.tasky.domain.user.UserRepo
import com.onetree.andresvergara.tasky.infraestructure.FakeSessionManager
import com.onetree.andresvergara.tasky.presentation.task.viewmodel.TaskViewModel
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.mobile
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
            geoLocationModule,
            sessionManagementModule,
            databaseModule(),
            provideDAOModule,
            provideDataSourceModule,
            provideRepositoryModule,
            provideUseCaseModule,
            provideViewModelModule,
        )
    }

expect fun databaseModule(): Module

val geoLocationModule = module {
    single<Geolocator> { Geolocator.mobile() }
}

val sessionManagementModule = module {
    single<SessionManager> { FakeSessionManager() }
}

val provideDAOModule = module {
    single<TaskDao> { get<TaskyDatabase>().taskDao() }
    single<UserDao> { get<TaskyDatabase>().userDao() }
}

val provideDataSourceModule = module {
    singleOf(::TaskLocalDataSource).bind(TaskDataSource::class)
    singleOf(::UserLocalDataSource).bind(UserDataSource::class)
}

val provideRepositoryModule = module {
    singleOf(::TaskRepoImpl).bind(TaskRepo::class)
    singleOf(::UserRepoImpl).bind(UserRepo::class)
}

val provideUseCaseModule = module {
    singleOf(::TaskDetailUseCaseImpl).bind(TaskDetailUseCase::class)
    singleOf(::CreateTaskUseCaseImpl).bind(CreateTaskUseCase::class)
    singleOf(::ListsTasksUseCaseImpl).bind(ListTasksUseCase::class)
    singleOf(::DeleteTasksUseCaseImpl).bind(DeleteTasksUseCase::class)
    singleOf(::MarkCompletedTasksUseCaseImpl).bind(MarkCompletedTasksUseCase::class)
}

val provideViewModelModule = module {
    viewModelOf(::TaskViewModel)
}