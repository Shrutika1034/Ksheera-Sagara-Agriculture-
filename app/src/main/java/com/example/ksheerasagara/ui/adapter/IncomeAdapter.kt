//package com.example.ksheerasagara.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.ksheerasagara.R
//import com.example.ksheerasagara.data.entity.MilkEntry
//
//class IncomeAdapter(
//    private val items: List<MilkEntry>
//) : RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//        val text: TextView = view.findViewById(R.id.itemText)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_income, parent, false)
//
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        val item = items[position]
//
//        holder.text.text =
//            "Milk: ${item.liters}L  ₹${item.totalPayment}"
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
import com.example.ksheerasagara.data.entity.MilkEntry
import com.example.ksheerasagara.databinding.ItemIncomeBinding

class IncomeAdapter : RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {

    private var list = listOf<MilkEntry>()

    fun submitList(newList: List<MilkEntry>) {
        list = newList
        notifyDataSetChanged()
    }

    fun filterList(filteredList: List<MilkEntry>) {
        submitList(filteredList)
    }

    inner class IncomeViewHolder(
        private val binding: ItemIncomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MilkEntry) {

            binding.tvLiters.text = "${item.liters} L"

            binding.tvPayment.text = "₹ ${item.totalPayment}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {

        val binding = ItemIncomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return IncomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        holder.bind(list[position])
    }
}