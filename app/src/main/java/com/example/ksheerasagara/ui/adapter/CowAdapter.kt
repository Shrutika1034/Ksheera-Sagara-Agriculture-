package com.example.ksheerasagara.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ksheerasagara.R
import com.example.ksheerasagara.data.entity.Cow

class CowAdapter(
    private val items: List<Cow>
) : RecyclerView.Adapter<CowAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val text: TextView = view.findViewById(R.id.itemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cow, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]

        holder.text.text =
            "${item.name} (${item.breed})"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}