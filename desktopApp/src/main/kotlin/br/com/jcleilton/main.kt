package br.com.jcleilton

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "open-song-tools",
    ) {
        MainDAWScreen()
    }
}