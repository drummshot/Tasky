package com.onetree.andresvergara.tasky.app

import android.app.Application
import com.onetree.andresvergara.tasky.app.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent

class TaskyApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            appDeclaration = { androidContext(this@TaskyApplication) },
        )
    }
}
