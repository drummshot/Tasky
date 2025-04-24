package com.onetree.andresvergara.tasky.presentation.base

import com.onetree.andresvergara.tasky.domain.ErrorCode

interface UIStateBase {
    val isLoading: Boolean
    val errorCode: ErrorCode?
    val successOperation: Boolean
}