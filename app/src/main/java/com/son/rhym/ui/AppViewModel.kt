package com.son.rhym.ui

import androidx.lifecycle.ViewModel
import com.son.rhym.ui.common.snackbar.SnackbarManager
import com.son.rhym.ui.common.snackbar.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class AppViewModel @Inject constructor(
    snackbarManager: SnackbarManager
) : ViewModel() {
    val messages: Flow<SnackbarMessage> = snackbarManager.messages
}