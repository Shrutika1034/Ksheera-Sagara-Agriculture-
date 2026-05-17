package com.example.ksheerasagara.analytics

import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*

object ChartManager {

    // ----------------------------
    // PIE CHART
    // ----------------------------

    fun setupExpensePieChart(
        pieChart: PieChart,
        analytics: ExpenseAnalytics
    ) {

        val entries = arrayListOf(

            PieEntry(
                analytics.feedExpense,
                "Feed"
            ),

            PieEntry(
                analytics.medicineExpense,
                "Medicine"
            ),

            PieEntry(
                analytics.laborExpense,
                "Labor"
            ),

            PieEntry(
                analytics.otherExpense,
                "Other"
            )
        )

        val dataSet = PieDataSet(
            entries,
            "Expense Breakdown"
        )

        dataSet.colors = listOf(
            Color.BLUE,
            Color.RED,
            Color.GREEN,
            Color.MAGENTA
        )

        val data = PieData(dataSet)

        pieChart.data = data

        pieChart.description.isEnabled = false

        pieChart.centerText = "Expenses"

        pieChart.invalidate()
    }

    // ----------------------------
    // BAR CHART
    // ----------------------------

    fun setupProfitBarChart(
        barChart: BarChart,
        analytics: ProfitAnalytics
    ) {

        val entries = arrayListOf(

            BarEntry(
                0f,
                analytics.income
            ),

            BarEntry(
                1f,
                analytics.expense
            ),

            BarEntry(
                2f,
                analytics.profit
            )
        )

        val dataSet = BarDataSet(
            entries,
            "Monthly Finance"
        )

        dataSet.color = Color.parseColor("#6200EE")

        val data = BarData(dataSet)

        barChart.data = data

        barChart.description.isEnabled = false

        barChart.invalidate()
    }

    // ----------------------------
    // LINE CHART
    // ----------------------------

    fun setupProfitTrendChart(
        lineChart: LineChart
    ) {

        val entries = arrayListOf(

            Entry(1f, 1000f),
            Entry(2f, 1500f),
            Entry(3f, 1200f),
            Entry(4f, 1800f),
            Entry(5f, 2400f)
        )

        val dataSet = LineDataSet(
            entries,
            "Profit Trend"
        )

        dataSet.color = Color.GREEN

        dataSet.valueTextColor = Color.BLACK

        val data = LineData(dataSet)

        lineChart.data = data

        lineChart.description.isEnabled = false

        lineChart.invalidate()
    }
}