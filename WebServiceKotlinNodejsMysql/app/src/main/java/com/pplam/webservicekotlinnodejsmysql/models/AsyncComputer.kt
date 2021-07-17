package com.pplam.webservicekotlinnodejsmysql.models

import android.app.ProgressDialog
import android.os.AsyncTask
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AsyncComputer(private val context: AppCompatActivity) : AsyncTask<Any, Any, Boolean>() {

    lateinit var progressDialog: ProgressDialog

    override fun onPreExecute() {
        super.onPreExecute()
        this.progressDialog = ProgressDialog.show(context, "", "", true)
    }

    override fun doInBackground(vararg params: Any?): Boolean {
        val computer = params[0] as Computer

        return try {
            return ComputersJsonWebService().addComputer(computer)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun onPostExecute(result: Boolean?) {
        this.progressDialog.dismiss()
        if(result == true)
            context.finish()
        else
            Toast.makeText(context, "Error al enviar los datos", Toast.LENGTH_SHORT).show()
    }

}