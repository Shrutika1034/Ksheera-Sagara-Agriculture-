//package com.example.ksheerasagara.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.ksheerasagara.R
//import com.example.ksheerasagara.data.entity.ExpenseEntry
//
//class ExpenseAdapter(
//    private val items: List<ExpenseEntry>
//) : RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//        val text: TextView = view.findViewById(R.id.itemText)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_expense, parent, false)
//
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        val item = items[position]
//
//        holder.text.text =
//            "${item.category}: ₹${item.amount}"
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//}

package com.example.ksheerasagara.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ksheerasagara.data.entity.ExpenseEntry
import com.example.ksheerasagara.databinding.ItemExpenseBinding

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private var list = listOf<ExpenseEntry>()

    fun submitList(newList: List<ExpenseEntry>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class ExpenseViewHolder(
        private val binding: ItemExpenseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExpenseEntry) {

            binding.tvCategory.text = item.category

            binding.tvAmount.text = "₹ ${item.amount}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {

        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ExpenseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(list[position])
    }
}