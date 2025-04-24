package com.onetree.andresvergara.tasky.presentation.ui.kit

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheet(
    showSheet: ModalBottomSheetState,
    content: @Composable () -> Unit
) {

    ModalBottomSheetLayout(
        sheetState = showSheet,
        sheetContent = {
            content()
        }
    ) {


    }
}

