package com.pplam.webservicekotlinnodejsmysql.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.pplam.webservicekotlinnodejsmysql.R
import com.pplam.webservicekotlinnodejsmysql.helpers.PublicData
import com.pplam.webservicekotlinnodejsmysql.helpers.Validations
import com.pplam.webservicekotlinnodejsmysql.models.Computer
import com.pplam.webservicekotlinnodejsmysql.models.Service
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class AddEditComputerActivity : AppCompatActivity() {

    private lateinit var etNameComputer: EditText
    private lateinit var etPriceComputer: EditText
    private lateinit var etBrandComputer: EditText
    private lateinit var etDescriptionComputer: EditText
    private lateinit var btnRecord: Button
    private lateinit var tvTitle: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        val position: Int = intent.getIntExtra("position", -1)

        this.title = "${if(position != -1) "Editando" else "Agregando"} computadora"

        this.etNameComputer = findViewById(R.id.etNameComputer)
        this.etPriceComputer = findViewById(R.id.etPriceComputer)
        this.etBrandComputer = findViewById(R.id.etBrandComputer)
        this.etDescriptionComputer = findViewById(R.id.etDescriptionComputer)
        this.btnRecord = findViewById(R.id.btnRecord)
        this.tvTitle = findViewById(R.id.TitleAddEdit)

        if(position != -1) {
            this.title = if(position != -1) "Editando computadora con id ${PublicData.computers[position].id}" else "Agregando computadora"
            this.tvTitle.text = "Form computer / Edit"
            this.etNameComputer.setText(PublicData.computers[position].name)
            this.etPriceComputer.setText(PublicData.computers[position].price.toString())
            this.etBrandComputer.setText(PublicData.computers[position].brand)
            this.etDescriptionComputer.setText(PublicData.computers[position].description)
        }

        this.btnRecord.setOnClickListener {
            if(!Validations.validationEditText(this, listOf(this.etNameComputer, this.etPriceComputer, this.etBrandComputer, this.etDescriptionComputer)))
                Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show()
            else {
                val computer = Computer(
                    if(position != -1) PublicData.computers[position].id else 0,
                    this.etNameComputer.text.toString(),
                    this.etPriceComputer.text.toString().toDouble(),
                    this.etBrandComputer.text.toString(),
                    this.etDescriptionComputer.text.toString()
                )
                this.showDialogYesNo("Are you sure?", "Are you sure to finish the operation?", position, computer)
            }
        }

    }

    private fun showDialogYesNo(title: String, message: String, position: Int, computer: Computer) {
        AlertDialog.Builder(this@AddEditComputerActivity)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton("Cancel") { _, _ -> }
            .setPositiveButton("Save") { _, _ ->
                GlobalScope.launch {
                    if(position != -1)
                        Service.updateComputer(this@AddEditComputerActivity, computer)
                    else
                        Service.saveComputers(this@AddEditComputerActivity, computer)
                }
                this.finish()}
            .show()
    }
}