package com.pplam.webservicesqlite.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pplam.webservicesqlite.R
import com.pplam.webservicesqlite.models.Computer

class ComputerAdapter(private val adapter: MutableList<Computer>) :
    RecyclerView.Adapter<ComputerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameComputer: TextView = view.findViewById(R.id.tvNameComputer)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_computer_list, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNameComputer.text = "${holder.tvNameComputer.text} ${adapter[position].name}"
    }

    override fun getItemCount(): Int = adapter.size

}