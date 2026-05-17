package com.example.ksheerasagara.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.ksheerasagara.databinding.ActivityMainBinding
import com.example.ksheerasagara.utils.ThemeManager
import com.example.ksheerasagara.ui.activity.SettingsActivity
import com.example.ksheerasagara.ui.activity.IncomeLogActivity
import com.example.ksheerasagara.ui.activity.ExpenseLogActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        ThemeManager.applyTheme(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.dashboardBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    DashboardActivity::class.java
                )
            )
        }

        binding.incomeBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    IncomeLogActivity::class.java
                )
            )
        }

        binding.expenseBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ExpenseLogActivity::class.java
                )
            )
        }
        binding.dashboardBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    DashboardActivity::class.java
                )
            )
        }

        binding.incomeBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    IncomeLogActivity::class.java
                )
            )
        }

        binding.expenseBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ExpenseLogActivity::class.java
                )
            )
        }

// SETTINGS BUTTON

        binding.settingsBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    SettingsActivity::class.java
                )
            )
        }
    }
}