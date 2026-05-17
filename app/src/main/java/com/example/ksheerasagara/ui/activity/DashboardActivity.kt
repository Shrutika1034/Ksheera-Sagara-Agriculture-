package com.example.ksheerasagara.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ksheerasagara.utils.ThemeManager
import com.example.ksheerasagara.R
import com.example.ksheerasagara.analytics.ChartManager
import com.example.ksheerasagara.analytics.ExpenseAnalytics
import com.example.ksheerasagara.analytics.ProfitAnalytics

import com.example.ksheerasagara.data.database.AppDatabase
import com.example.ksheerasagara.data.repository.DataRepository

import com.example.ksheerasagara.databinding.ActivityDashboardBinding

import com.example.ksheerasagara.pdf.PDFGenerator
import com.example.ksheerasagara.pdf.PDFShareManager

import com.example.ksheerasagara.viewmodel.DashboardViewModel
import com.example.ksheerasagara.viewmodel.ViewModelFactory

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    private lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)

        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(
            layoutInflater
        )

        setContentView(binding.root)

        // DATABASE

        val database = AppDatabase.getDatabase(this)

        val repository = DataRepository(
            database.milkEntryDao(),
            database.expenseEntryDao(),
            database.cowDao(),
            database.cowProductionDao(),
            database.cowExpenseDao()
        )

        val factory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(
            this,
            factory
        )[DashboardViewModel::class.java]

        observeDashboard()

        setupCharts()

        // INCOME BUTTON

        binding.btnIncome.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    IncomeLogActivity::class.java
                )
            )
        }

        // EXPENSE BUTTON

        binding.btnExpense.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ExpenseLogActivity::class.java
                )
            )
        }

        // PDF BUTTON

        binding.btnGeneratePdf.setOnClickListener {

            val file = PDFGenerator.generateMonthlyReport(
                this,
                emptyList(),
                emptyList(),
                10000.0,
                5000.0
            )

            PDFShareManager.sharePdf(
                this,
                file
            )
        }
    }

    // ----------------------------
    // OBSERVE DASHBOARD
    // ----------------------------

    private fun observeDashboard() {

        viewModel.monthlyIncome.observe(this) { income ->

            binding.tvIncome.text =
                "Total Income: ₹ $income"

            val expense =
                viewModel.monthlyExpenses.value ?: 0.0

            updateProfit(income, expense)
        }

        viewModel.monthlyExpenses.observe(this) { expense ->

            binding.tvExpense.text =
                "Total Expense: ₹ $expense"

            val income =
                viewModel.monthlyIncome.value ?: 0.0

            updateProfit(income, expense)
        }
    }

    private fun updateProfit(
        income: Double,
        expense: Double
    ) {

        val profit = income - expense

        binding.tvProfit.text =
            "Profit/Loss: ₹ $profit"

        if (profit >= 0) {

            binding.tvProfit.setTextColor(
                getColor(R.color.profit_green)
            )

        } else {

            binding.tvProfit.setTextColor(
                getColor(R.color.loss_red)
            )
        }
    }

    // ----------------------------
    // CHARTS
    // ----------------------------

    private fun setupCharts() {

        val expenseAnalytics = ExpenseAnalytics(

            feedExpense = 3000f,

            medicineExpense = 1200f,

            laborExpense = 2000f,

            otherExpense = 800f
        )

        ChartManager.setupExpensePieChart(
            binding.pieChart,
            expenseAnalytics
        )

        val profitAnalytics = ProfitAnalytics(

            income = 12000f,

            expense = 7000f,

            profit = 5000f
        )

        ChartManager.setupProfitBarChart(
            binding.barChart,
            profitAnalytics
        )

        ChartManager.setupProfitTrendChart(
            binding.lineChart
        )
    }
}