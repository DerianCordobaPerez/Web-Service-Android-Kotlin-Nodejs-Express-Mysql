package com.pplam.webservicekotlinnodejsmysql.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pplam.webservicekotlinnodejsmysql.R
import com.pplam.webservicekotlinnodejsmysql.models.Computer

class ComputerAdapter(private val adapter: MutableList<Computer>) :
    RecyclerView.Adapter<ComputerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameComputer: TextView = view.findViewById(R.id.tvNameComputer)
        val tvPriceComputer: TextView = view.findViewById(R.id.tvPriceComputer)
        val tvBrandComputer: TextView = view.findViewById(R.id.tvBrandComputer)
        val tvDescriptionComputer: TextView = view.findViewById(R.id.tvDescriptionComputer)
        val btnEditComputer: Button = view.findViewById(R.id.btnEditComputer)
        val btnDeleteComputer: Button = view.findViewById(R.id.btnDeleteComputer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_computer_list, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNameComputer.text = "${holder.tvNameComputer.text} ${adapter[position].name}"
        holder.tvPriceComputer.text = "${holder.tvPriceComputer.text} ${adapter[position].price}"
        holder.tvBrandComputer.text = "${holder.tvBrandComputer.text} ${adapter[position].brand}"
        holder.tvDescriptionComputer.text = "${holder.tvDescriptionComputer.text} ${adapter[position].description}"

        holder.btnEditComputer.setOnClickListener {

        }

        holder.btnDeleteComputer.setOnClickListener {

        }

    }

    override fun getItemCount(): Int = adapter.size

}