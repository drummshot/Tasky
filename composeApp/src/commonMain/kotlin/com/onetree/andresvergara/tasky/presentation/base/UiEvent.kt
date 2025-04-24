package com.onetree.andresvergara.tasky.presentation.base

sealed class UiEvent {
    class IDLE : UiEvent()
    data class ShowSnackbar(val success: Boolean) : UiEvent()
}