package com.son.rhym.ui.common.snackbar

import android.content.Context
import androidx.annotation.StringRes

sealed interface UiText {
    data class Dynamic(val value: String) : UiText
    class Res(@StringRes val id: Int, vararg val args: Any) : UiText

    fun asString(context: Context): String = when (this) {
        is Dynamic -> value
        is Res -> context.getString(id, *args)
    }
}