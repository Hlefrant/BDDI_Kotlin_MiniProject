package com.hugolefrant.miniproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hugolefrant.miniproject.R
import models.Mode


class ModeAdapter(private val dataset: List<Mode>, val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ModeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.mode_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataset[position], itemClickListener)

    override fun getItemCount(): Int = dataset.size

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(item: Mode, clickListener: OnItemClickListener) {
            val txtviewName = root.findViewById<TextView>(R.id.mode_name)
            txtviewName.text = item.name

            root.setOnClickListener {
                clickListener.onItemClicked(item)
            }
        }
    }
}

interface OnItemClickListener{
    fun onItemClicked(mode: Mode)
}