package com.example.ksheerasagara.utils

import android.content.Context
import android.os.Environment
import com.example.ksheerasagara.data.entity.MonthlySummary
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class PDFGenerator(
    private val context: Context
) {

    fun generateMonthlySummaryPDF(
        summary: MonthlySummary
    ): Boolean {

        return try {

            val documentsDir = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS
                ),
                "KsheeraSagara"
            )

            if (!documentsDir.exists()) {
                documentsDir.mkdirs()
            }

            val document = Document(PageSize.A4)

            val pdfFile = File(
                documentsDir,
                "Summary_${System.currentTimeMillis()}.pdf"
            )

            PdfWriter.getInstance(
                document,
                FileOutputStream(pdfFile)
            )

            document.open()

            val title = Paragraph(
                "KSHEERA-SAGARA",
                Font(
                    Font.FontFamily.HELVETICA,
                    24f,
                    Font.BOLD
                )
            )

            title.alignment = Element.ALIGN_CENTER

            document.add(title)

            document.add(Paragraph(" "))

            val table = PdfPTable(2)

            table.widthPercentage = 100f

            table.addCell("Total Income")
            table.addCell("₹${summary.totalIncome}")

            table.addCell("Total Expenses")
            table.addCell("₹${summary.totalExpenses}")

            table.addCell("Net Profit")
            table.addCell("₹${summary.netProfit}")

            document.add(table)

            document.close()

            true

        } catch (e: Exception) {

            e.printStackTrace()

            false
        }
    }
}