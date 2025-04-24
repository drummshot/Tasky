package com.onetree.andresvergara.tasky.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.onetree.andresvergara.tasky.presentation.Navigation
import com.onetree.andresvergara.tasky.presentation.ui.kit.PastelMintSkyColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = PastelMintSkyColors
    ) {
        Navigation()
    }
}