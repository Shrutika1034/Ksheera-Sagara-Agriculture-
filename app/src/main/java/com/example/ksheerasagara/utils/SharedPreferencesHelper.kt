package com.example.ksheerasagara.utils

import android.content.Context

class SharedPreferencesHelper(
    context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "ksheera_sagara_prefs",
            Context.MODE_PRIVATE
        )

    fun saveUserPreference(
        key: String,
        value: String
    ) {

        prefs.edit()
            .putString(key, value)
            .apply()
    }

    fun getUserPreference(
        key: String,
        defaultValue: String = ""
    ): String {

        return prefs.getString(
            key,
            defaultValue
        ) ?: defaultValue
    }
}