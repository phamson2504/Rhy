package com.son.rhym.ui.setting

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

fun changeAppLanguage(languageTag: String) {
    AppCompatDelegate.setApplicationLocales(
        LocaleListCompat.forLanguageTags(languageTag)
    )
}