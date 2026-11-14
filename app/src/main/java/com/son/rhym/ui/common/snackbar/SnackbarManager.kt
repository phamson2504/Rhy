package com.son.rhym.ui.common.snackbar

import androidx.compose.material3.SnackbarDuration
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

data class SnackbarMessage(
    val text: UiText,
    val duration: SnackbarDuration = SnackbarDuration.Short
)

@Singleton
class SnackbarManager @Inject constructor() {
    private val _messages = Channel<SnackbarMessage>(Channel.BUFFERED)
    val messages: Flow<SnackbarMessage> = _messages.receiveAsFlow()

    fun show(text: UiText, duration: SnackbarDuration = SnackbarDuration.Short) {
        _messages.trySend(SnackbarMessage(text, duration))
    }
}