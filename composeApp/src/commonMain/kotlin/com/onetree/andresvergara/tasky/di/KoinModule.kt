package com.onetree.andresvergara.tasky.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(config: KoinApplication? = null) =
    startKoin {
        modules(
            provideDataSourceModule,
            provideRepositoryModule,
            provideUseCaseModule,
            provideViewModelModule
        )
    }

val provideDataSourceModule = module {
    //singleOf(::NoteLocalDataSourceImpl).bind(NoteLocalDataSource::class)
}

val provideRepositoryModule = module {
    //singleOf(::NoteLocalDataSourceImpl).bind(NoteLocalDataSource::class)
}

val provideUseCaseModule = module {

}

val provideViewModelModule = module {

}