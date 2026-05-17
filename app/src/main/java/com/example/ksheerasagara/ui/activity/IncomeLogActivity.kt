package com.example.ksheerasagara.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.ksheerasagara.data.database.AppDatabase
import com.example.ksheerasagara.data.entity.MilkEntry
import com.example.ksheerasagara.data.repository.DataRepository
import com.example.ksheerasagara.databinding.ActivityIncomeLogBinding
import com.example.ksheerasagara.ui.adapter.IncomeAdapter
import com.example.ksheerasagara.viewmodel.IncomeLogViewModel
import com.example.ksheerasagara.viewmodel.ViewModelFactory

class IncomeLogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIncomeLogBinding

    private lateinit var viewModel: IncomeLogViewModel

    private lateinit var incomeAdapter: IncomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityIncomeLogBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // BACK BUTTON
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        ).get(IncomeLogViewModel::class.java)

        setupRecyclerView()

        observeData()

        // SEARCH

        binding.searchView.setOnQueryTextListener(

            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(
                    query: String?
                ): Boolean {

                    return false
                }

                override fun onQueryTextChange(
                    newText: String?
                ): Boolean {

                    val filteredList =
                        viewModel.allMilkEntries.value?.filter {

                            (it.notes ?: "").contains(
                                newText ?: "",
                                ignoreCase = true
                            )

                        } ?: emptyList()

                    incomeAdapter.filterList(filteredList)

                    return true
                }
            }
        )

        // SAVE BUTTON

        binding.btnSaveIncome.setOnClickListener {

            saveIncome()
        }
    }

    // BACK BUTTON ACTION

    override fun onSupportNavigateUp(): Boolean {

        finish()

        return true
    }

    private fun setupRecyclerView() {

        incomeAdapter = IncomeAdapter()

        binding.recyclerIncome.apply {

            layoutManager =
                LinearLayoutManager(this@IncomeLogActivity)

            adapter = incomeAdapter
        }
    }

    private fun observeData() {

        viewModel.allMilkEntries.observe(this) {

            incomeAdapter.submitList(it)
        }
    }

    private fun saveIncome() {

        val liters =
            binding.etLiters.text.toString()
                .toDoubleOrNull() ?: 0.0

        val rate =
            binding.etRate.text.toString()
                .toDoubleOrNull() ?: 0.0

        val notes =
            binding.etNotes.text.toString()

        // VALIDATION

        if (liters <= 0 || rate <= 0) {

            android.widget.Toast.makeText(
                this,
                "Enter valid values",
                android.widget.Toast.LENGTH_SHORT
            ).show()

            return
        }

        // PREVENT FAST CLICKING

        binding.btnSaveIncome.isEnabled = false

        val entry = MilkEntry(
            cowId = 1,
            liters = liters,
            ratePerLiter = rate,
            notes = notes
        )

        viewModel.insertMilkEntry(entry)

        binding.etLiters.text?.clear()

        binding.etRate.text?.clear()

        binding.etNotes.text?.clear()

        // ENABLE AGAIN

        binding.btnSaveIncome.postDelayed({

            binding.btnSaveIncome.isEnabled = true

        }, 1000)
    }
}
