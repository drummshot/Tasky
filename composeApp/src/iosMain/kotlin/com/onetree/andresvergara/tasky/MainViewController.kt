package com.onetree.andresvergara.tasky

import androidx.compose.ui.window.ComposeUIViewController
import com.onetree.andresvergara.tasky.app.di.App

fun MainViewController() = ComposeUIViewController { App() }