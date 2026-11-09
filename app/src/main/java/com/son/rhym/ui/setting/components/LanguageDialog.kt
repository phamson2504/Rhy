package com.son.rhym.ui.setting.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.son.rhym.ui.setting.changeAppLanguage

private const val TAG = "LanguageDialog"

@Composable
fun LanguageDialog(
    onDismiss: () -> Unit
) {
    var isChanging by remember { mutableStateOf(false) }

    fun selectLanguage(languageTag: String) {
        if (isChanging) return

        isChanging = true

        Log.d(TAG, "Selected language: $languageTag")

        try {
            onDismiss()
            changeAppLanguage(languageTag)

            Log.d(TAG, "Language change requested successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to change language", e)
            isChanging = false
        }
    }

    AlertDialog(
        onDismissRequest = {
            if (!isChanging) onDismiss()
        },
        title = {
            Text("App language")
        },
        text = {
            Column {
                TextButton(
                    enabled = !isChanging,
                    onClick = {
                        selectLanguage("en")
                    }
                ) {
                    Text(
                        text = "🇬🇧 English",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                TextButton(
                    enabled = !isChanging,
                    onClick = {
                        selectLanguage("vi")
                    }
                ) {
                    Text(
                        text = "🇻🇳 Tiếng Việt",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                TextButton(
                    enabled = !isChanging,
                    onClick = {
                        selectLanguage("")
                    }
                ) {
                    Text(
                        text = "System default",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        },
        confirmButton = {}
    )
}
