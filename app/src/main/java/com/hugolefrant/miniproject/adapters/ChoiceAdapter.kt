package com.hugolefrant.miniproject.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hugolefrant.miniproject.R
import models.Category
import models.Country
import models.Source
import models.SourceItem


class ChoiceAdapter(private val dataset: List<Any>, val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ChoiceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.choice_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataset[position], itemClickListener)

    override fun getItemCount(): Int = dataset.size

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Any, clickListener: OnItemClickListener) {
            val txtviewName = root.findViewById<TextView>(R.id.choice_name)

            when(item) {
                is SourceItem -> txtviewName.text = item.name
                is Category -> txtviewName.text = item.title
                is Country -> txtviewName.text = item.name
            }

            root.setOnClickListener {
                clickListener.onItemClicked(item)
            }
        }
    }
}