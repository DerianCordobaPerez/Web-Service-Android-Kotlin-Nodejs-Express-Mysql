package com.pplam.webservicesqlite.models

import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class ComputersJsonWebService : JsonWebService() {

    private val urlApi: String = "https://localhost:4000/api/computer/"

    fun getComputers(): ArrayList<Computer> {
        var computers: ArrayList<Computer>? = null
        val json: JSONObject? = this.getJson("${this.urlApi}getAll")

        if(json != null) {
            val array: JSONArray = json.getJSONArray("computers")
            computers = ArrayList()

            for(i: Int in 0..array.length()) {
                val jsonObject: JSONObject = array.getJSONObject(i)
                computers.add(Computer(
                        jsonObject.getInt("id"),
                        jsonObject.getString("name"),
                        jsonObject.getDouble("price"),
                        jsonObject.getString("brand"),
                        jsonObject.getString("description")
                ))
            }
        }
        return computers!!
    }

    fun addComputer(computer: Computer): Boolean {
        val json = JSONObject()
        json.put("name", computer.name)
        json.put("price", computer.price)
        json.put("brand", computer.brand)
        json.put("description", computer.description)

        val jsonResult: JSONObject = this.sendJson("${this.urlApi}add", json) ?: return false

        return jsonResult.getString("message").compareTo("correct") == 0

    }

}