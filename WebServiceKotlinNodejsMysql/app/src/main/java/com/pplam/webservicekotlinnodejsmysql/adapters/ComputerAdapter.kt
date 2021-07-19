package com.pplam.webservicekotlinnodejsmysql.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pplam.webservicekotlinnodejsmysql.R
import com.pplam.webservicekotlinnodejsmysql.activitys.AddEditComputerActivity
import com.pplam.webservicekotlinnodejsmysql.helpers.PublicData
import com.pplam.webservicekotlinnodejsmysql.models.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ComputerAdapter() :
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

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val adapter = PublicData.computers

        holder.tvNameComputer.text = "Name: ${adapter[position].name}"
        holder.tvPriceComputer.text = "Price: ${adapter[position].price}"
        holder.tvBrandComputer.text = "Brand: ${adapter[position].brand}"
        holder.tvDescriptionComputer.text = "Description: ${adapter[position].description}"

        holder.btnEditComputer.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context, AddEditComputerActivity::class.java).apply {
                putExtra("position", position)
            })
        }

        holder.btnDeleteComputer.setOnClickListener {
            Log.e("Delete", "Deleting computer")
            GlobalScope.launch {
                Service.deleteComputer(holder.itemView.context, adapter[position].id)
                Service.getAllComputer(this@ComputerAdapter)
            }
        }

    }

    override fun getItemCount(): Int = PublicData.computers.size

}