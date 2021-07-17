package com.pplam.webservicekotlinnodejsmysql.models

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class ComputersJsonWebService : JsonWebService() {

    private val urlApi: String = "http://10.0.0.2:4000/api/computer/"

    fun getComputers(): ArrayList<Computer> {
        val computers: ArrayList<Computer> = ArrayList()

        val json = this.getJson("${this.urlApi}getAll") ?: return computers

        for(i: Int in 0 until json.length()) {
            val jsonObject: JSONObject = json.getJSONObject(i)
            computers.add(Computer(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    jsonObject.getDouble("price"),
                    jsonObject.getString("brand"),
                    jsonObject.getString("description")
            ))
        }
        return computers
    }

    fun addComputer(computer: Computer): Boolean {
        val json = JSONObject()
        json.put("name", computer.name)
        json.put("price", computer.price)
        json.put("brand", computer.brand)
        json.put("description", computer.description)

        val jsonResult: JSONObject = this.sendJson("${this.urlApi}add", json) ?: return false

        return jsonResult.getString("status").compareTo("success") == 0

    }

}