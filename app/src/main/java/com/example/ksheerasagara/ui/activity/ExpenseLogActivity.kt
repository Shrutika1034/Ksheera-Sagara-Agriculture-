package com.example.ksheerasagara.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.ksheerasagara.data.database.AppDatabase
import com.example.ksheerasagara.data.entity.ExpenseEntry
import com.example.ksheerasagara.data.repository.DataRepository
import com.example.ksheerasagara.databinding.ActivityExpenseLogBinding
import com.example.ksheerasagara.ui.adapter.ExpenseAdapter
import com.example.ksheerasagara.viewmodel.ExpenseLogViewModel
import com.example.ksheerasagara.viewmodel.ViewModelFactory

class ExpenseLogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpenseLogBinding

    private lateinit var adapter: ExpenseAdapter

    private lateinit var viewModel: ExpenseLogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityExpenseLogBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // BACK BUTTON

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewModel()

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
                        viewModel.allExpenses.value?.filter {

                            it.description.contains(
                                newText ?: "",
                                ignoreCase = true
                            )

                        } ?: emptyList()

                    adapter.submitList(filteredList)

                    return true
                }
            }
        )

        // SAVE BUTTON

        binding.btnSaveExpense.setOnClickListener {

            val amount =
                binding.etAmount.text.toString()
                    .toDoubleOrNull() ?: 0.0

            val description =
                binding.etDescription.text.toString()

            // VALIDATION

            if (amount <= 0) {

                Toast.makeText(
                    this,
                    "Enter valid expense",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // PREVENT FAST CLICKING

            binding.btnSaveExpense.isEnabled = false

            val entry = ExpenseEntry(

                category = "Feed",

                amount = amount,

                description = description
            )

            viewModel.insertExpense(entry)

            Toast.makeText(
                this,
                "Expense Saved",
                Toast.LENGTH_SHORT
            ).show()

            // CLEAR FIELDS

            binding.etAmount.text?.clear()

            binding.etDescription.text?.clear()

            // ENABLE AGAIN

            binding.btnSaveExpense.postDelayed({

                binding.btnSaveExpense.isEnabled = true

            }, 1000)
        }
    }

    // BACK BUTTON ACTION

    override fun onSupportNavigateUp(): Boolean {

        finish()

        return true
    }

    private fun setupViewModel() {

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
        )[ExpenseLogViewModel::class.java]
    }

    private fun setupRecyclerView() {

        adapter = ExpenseAdapter()

        binding.recyclerExpense.layoutManager =
            LinearLayoutManager(this)

        binding.recyclerExpense.adapter = adapter
    }

    private fun observeData() {

        viewModel.allExpenses.observe(this) {

            adapter.submitList(it)
        }
    }
}