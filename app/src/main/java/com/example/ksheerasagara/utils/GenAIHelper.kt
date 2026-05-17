package com.example.ksheerasagara.utils

class GenAIHelper {

    fun categorizeExpenseDescription(
        description: String
    ): String {

        return when {

            description.lowercase().contains("feed") ->
                "Feed"

            description.lowercase().contains("medicine") ->
                "Medicine"

            description.lowercase().contains("labor") ->
                "Labor"

            else ->
                "Other"
        }
    }
}