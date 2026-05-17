package com.example.ksheerasagara.pdf

import android.content.Context
import android.os.Environment
import com.example.ksheerasagara.data.entity.ExpenseEntry
import com.example.ksheerasagara.data.entity.MilkEntry
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream

object PDFGenerator {

    fun generateMonthlyReport(
        context: Context,
        incomeList: List<MilkEntry>,
        expenseList: List<ExpenseEntry>,
        totalIncome: Double,
        totalExpense: Double
    ): File {

        val netProfit = totalIncome - totalExpense

        val document = Document()

        val directory = File(
            context.getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS
            ),
            "reports"
        )

        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(
            directory,
            "monthly_report.pdf"
        )

        PdfWriter.getInstance(
            document,
            FileOutputStream(file)
        )

        document.open()

        addTitle(document)

        addIncomeSection(
            document,
            incomeList
        )

        addExpenseSection(
            document,
            expenseList
        )

        addSummarySection(
            document,
            totalIncome,
            totalExpense,
            netProfit
        )

        document.close()

        return file
    }

    private fun addTitle(
        document: Document
    ) {

        val title = Paragraph(
            "Ksheera-Sagara Monthly Report",
            Font(
                Font.FontFamily.HELVETICA,
                22f,
                Font.BOLD
            )
        )

        title.spacingAfter = 24f

        document.add(title)
    }

    private fun addIncomeSection(
        document: Document,
        incomes: List<MilkEntry>
    ) {

        val heading = Paragraph(
            "Income Entries",
            Font(
                Font.FontFamily.HELVETICA,
                18f,
                Font.BOLD
            )
        )

        heading.spacingAfter = 12f

        document.add(heading)

        incomes.forEach {

            document.add(
                Paragraph(
                    "Liters: ${it.liters} | Payment: ₹${it.totalPayment}"
                )
            )
        }

        document.add(Chunk.NEWLINE)
    }

    private fun addExpenseSection(
        document: Document,
        expenses: List<ExpenseEntry>
    ) {

        val heading = Paragraph(
            "Expense Entries",
            Font(
                Font.FontFamily.HELVETICA,
                18f,
                Font.BOLD
            )
        )

        heading.spacingAfter = 12f

        document.add(heading)

        expenses.forEach {

            document.add(
                Paragraph(
                    "${it.category} : ₹${it.amount}"
                )
            )
        }

        document.add(Chunk.NEWLINE)
    }

    private fun addSummarySection(
        document: Document,
        income: Double,
        expense: Double,
        profit: Double
    ) {

        val heading = Paragraph(
            "Financial Summary",
            Font(
                Font.FontFamily.HELVETICA,
                18f,
                Font.BOLD
            )
        )

        heading.spacingAfter = 12f

        document.add(heading)

        document.add(
            Paragraph("Total Income : ₹$income")
        )

        document.add(
            Paragraph("Total Expense : ₹$expense")
        )

        document.add(
            Paragraph("Net Profit : ₹$profit")
        )

        val health = if (profit >= 0) {
            "PROFITABLE"
        } else {
            "LOSS"
        }

        document.add(
            Paragraph("Financial Health : $health")
        )
    }
}