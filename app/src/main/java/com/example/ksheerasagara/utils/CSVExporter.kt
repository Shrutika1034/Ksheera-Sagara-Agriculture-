package com.example.ksheerasagara.utils

import android.content.Context
import com.example.ksheerasagara.data.entity.ExpenseEntry
import com.example.ksheerasagara.data.entity.MilkEntry
import java.io.File

object CSVExporter {

    fun exportIncomeCsv(
        context: Context,
        entries: List<MilkEntry>
    ): File {

        val file = File(
            context.cacheDir,
            "income.csv"
        )

        file.bufferedWriter().use { writer ->

            writer.write(
                "Liters,Rate,Payment\n"
            )

            entries.forEach {

                writer.write(
                    "${it.liters},${it.ratePerLiter},${it.totalPayment}\n"
                )
            }
        }

        return file
    }

    fun exportExpenseCsv(
        context: Context,
        entries: List<ExpenseEntry>
    ): File {

        val file = File(
            context.cacheDir,
            "expenses.csv"
        )

        file.bufferedWriter().use { writer ->

            writer.write(
                "Category,Amount\n"
            )

            entries.forEach {

                writer.write(
                    "${it.category},${it.amount}\n"
                )
            }
        }

        return file
    }
}