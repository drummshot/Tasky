package com.onetree.andresvergara.tasky.presentation.base

import com.onetree.andresvergara.tasky.domain.Error

interface UIStateBase {
    val isLoading: Boolean
    val errorCode: Error?
}