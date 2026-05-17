package com.example.ksheerasagara.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemeManager {

    private const val PREF_NAME = "theme_pref"

    private const val KEY_THEME = "theme_mode"

    fun saveTheme(
        context: Context,
        mode: Int
    ) {

        val pref =
            context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )

        pref.edit()
            .putInt(KEY_THEME, mode)
            .apply()

        AppCompatDelegate.setDefaultNightMode(mode)
    }

    fun applyTheme(
        context: Context
    ) {

        val pref =
            context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
            )

        val mode =
            pref.getInt(
                KEY_THEME,
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}