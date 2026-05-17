//// PDFGenerator.kt
//// Location: app/src/main/java/com/mindmatrix/ksheera_sagara/utils/
//
//package com.example.ksheerasagara.utils
//
//import android.content.Context
//import com.itextpdf.text.pdf.PdfPCell
//import com.itextpdf.text.pdf.PdfPTable
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//import android.os.Environment
//import com.itextpdf.text.*
//import com.itextpdf.text.pdf.PdfWriter
//import com.example.ksheerasagara.data.entity.MonthlySummary
//import java.io.File
//import java.io.FileOutputStream
//
//class PDFGenerator(private val context: Context) {
//
//    fun generateMonthlySummaryPDF(summary: MonthlySummary): Boolean {
//        return try {
//            // Create directory if not exists
//            val documentsDir = File(
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
//                "KsheeraSagara"
//            )
//            if (!documentsDir.exists()) {
//                documentsDir.mkdirs()
//            }
//
//            // Create PDF document
//            val document = Document(PageSize.A4)
//            val pdfFile = File(documentsDir, "Ksheera_Sagara_Summary_${System.currentTimeMillis()}.pdf")
//            val writer = PdfWriter.getInstance(document, FileOutputStream(pdfFile))
//
//            document.open()
//
//            // Title
//            val titleFont = Font(Font.FontFamily.HELVETICA, 24f, Font.BOLD, BaseColor.BLACK)
//            val title = Paragraph("KSHEERA-SAGARA", titleFont)
//            title.alignment = Element.ALIGN_CENTER
//            document.add(title)
//
//            val subtitle = Paragraph("Monthly Financial Summary")
//            subtitle.alignment = Element.ALIGN_CENTER
//            document.add(subtitle)
//
//            document.add(Paragraph(" "))
//
//            // Date
//            val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
//            val dateText = Paragraph("Report Date: ${dateFormat.format(Date())}")
//            document.add(dateText)
//            document.add(Paragraph(" "))
//
//            // Summary Table
//            val table = PdfPTable(2)
//            table.widthPercentage = 100f
//
//            // Headers
//            val headerCell = PdfPCell(Paragraph("Metric", Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD)))
//            headerCell.backgroundColor = BaseColor.LIGHT_GRAY
//            table.addCell(headerCell)
//
//            val valueCell = PdfPCell(Paragraph("Amount", Font(Font.FontFamily.HELVETICA, 12f, Font.BOLD)))
//            valueCell.backgroundColor = BaseColor.LIGHT_GRAY
//            table.addCell(valueCell)
//
//            // Data rows
//            table.addCell("Total Income")
//            table.addCell("₹${String.format("%.2f", summary.totalIncome)}")
//
//            table.addCell("Total Expenses")
//            table.addCell("₹${String.format("%.2f", summary.totalExpenses)}")
//
//            table.addCell("Net Profit")
//            table.addCell("₹${String.format("%.2f", summary.netProfit)}")
//
//            table.addCell("Profit Margin")
//            table.addCell("${String.format("%.2f", summary.profitMargin)}%")
//
//            table.addCell("Days Tracked")
//            table.addCell("${summary.daysTracked}/30")
//
//            document.add(table)
//            document.add(Paragraph(" "))
//
//            // Recommendations
//            val recTitle = Paragraph("Recommendations:", Font(Font.FontFamily.HELVETICA, 14f, Font.BOLD))
//            document.add(recTitle)
//
//            val recommendations = listOf(
//                "• Monitor feed costs regularly",
//                "• Maintain preventive health care for cows",
//                "• Track cow-wise profitability monthly",
//                "• Update records daily for accuracy"
//            )
//
//            for (rec in recommendations) {
//                document.add(Paragraph(rec))
//            }
//
//            document.close()
//            writer.close()
//
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }
//    }
//}
//
//// GenAIHelper.kt
//class GenAIHelper {
//
//    fun getSuggestionsForExpenseOptimization(
//        totalIncome: Double,
//        feedExpense: Double,
//        medicineExpense: Double,
//        laborExpense: Double
//    ): List<String> {
//        val suggestions = mutableListOf<String>()
//
//        // Feed cost optimization
//        val feedPercentage = (feedExpense / totalIncome) * 100
//        if (feedPercentage > 40) {
//            suggestions.add("Feed costs are ${String.format("%.1f", feedPercentage)}% of income. Consider switching to home-grown fodder to save ₹${(feedExpense * 0.15).toInt()}/month")
//        }
//
//        // Medicine cost check
//        if (medicineExpense > 0) {
//            suggestions.add("Maintain preventive care schedule to reduce future medicine costs")
//        }
//
//        // Labor optimization
//        if (laborExpense > 0) {
//            suggestions.add("Review labor schedule for efficiency. Current cost: ₹${laborExpense.toInt()}/month")
//        }
//
//        // General suggestions
//        suggestions.add("Analyze cow-wise profitability to identify non-productive animals")
//
//        return suggestions
//    }
//
//    fun categorizExpenseDescription(description: String): String {
//        return when {
//            description.lowercase().contains("feed") ||
//            description.lowercase().contains("fodder") ||
//            description.lowercase().contains("grass") -> "Feed"
//
//            description.lowercase().contains("medicine") ||
//            description.lowercase().contains("doctor") ||
//            description.lowercase().contains("vaccine") ||
//            description.lowercase().contains("health") -> "Medicine"
//
//            description.lowercase().contains("labor") ||
//            description.lowercase().contains("worker") ||
//            description.lowercase().contains("wage") -> "Labor"
//
//            else -> "Other"
//        }
//    }
//}
//
//// DateUtils.kt
//object DateUtils {
//
//    fun getMonthStart(): Long {
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
//        calendar.set(Calendar.HOUR_OF_DAY, 0)
//        calendar.set(Calendar.MINUTE, 0)
//        calendar.set(Calendar.SECOND, 0)
//        return calendar.timeInMillis
//    }
//
//    fun getMonthEnd(): Long {
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.MONTH, 1)
//        calendar.add(Calendar.DAY_OF_MONTH, -1)
//        calendar.set(Calendar.HOUR_OF_DAY, 23)
//        calendar.set(Calendar.MINUTE, 59)
//        calendar.set(Calendar.SECOND, 59)
//        return calendar.timeInMillis
//    }
//
//    fun formatDate(millis: Long): String {
//        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
//        return dateFormat.format(Date(millis))
//    }
//
//    fun formatCurrency(amount: Double): String {
//        return "₹${String.format("%.2f", amount)}"
//    }
//}
//
//// Constants.kt
//object Constants {
//    const val DATABASE_NAME = "ksheera_sagara_db"
//
//    const val GENAI_BASE_URL = "https://api.anthropic.com/v1/"
//    const val GENAI_TIMEOUT = 30L
//
//    const val EXPENSE_CATEGORY_FEED = "Feed"
//    const val EXPENSE_CATEGORY_MEDICINE = "Medicine"
//    const val EXPENSE_CATEGORY_LABOR = "Labor"
//    const val EXPENSE_CATEGORY_OTHER = "Other"
//
//    val EXPENSE_CATEGORIES = listOf(
//        EXPENSE_CATEGORY_FEED,
//        EXPENSE_CATEGORY_MEDICINE,
//        EXPENSE_CATEGORY_LABOR,
//        EXPENSE_CATEGORY_OTHER
//    )
//}
//
//// SharedPreferencesHelper.kt
//class SharedPreferencesHelper(context: Context) {
//    private val prefs = context.getSharedPreferences("ksheera_sagara_prefs", Context.MODE_PRIVATE)
//
//    fun saveGenAIApiKey(apiKey: String) {
//        prefs.edit().putString("genai_api_key", apiKey).apply()
//    }
//
//    fun getGenAIApiKey(): String? {
//        return prefs.getString("genai_api_key", null)
//    }
//
//    fun saveLastBackupTime(timeInMillis: Long) {
//        prefs.edit().putLong("last_backup_time", timeInMillis).apply()
//    }
//
//    fun getLastBackupTime(): Long {
//        return prefs.getLong("last_backup_time", 0)
//    }
//
//    fun saveUserPreference(key: String, value: String) {
//        prefs.edit().putString(key, value).apply()
//    }
//
//    fun getUserPreference(key: String, defaultValue: String = ""): String {
//        return prefs.getString(key, defaultValue) ?: defaultValue
//    }
//}
//
//
