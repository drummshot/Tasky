package com.onetree.andresvergara.tasky

import androidx.compose.ui.window.ComposeUIViewController
import com.onetree.andresvergara.tasky.app.App
import com.onetree.andresvergara.tasky.app.di.initKoin


fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin(appDeclaration = {})
    }) { App() }