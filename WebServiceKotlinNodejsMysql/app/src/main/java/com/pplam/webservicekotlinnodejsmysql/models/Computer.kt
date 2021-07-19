package com.pplam.webservicekotlinnodejsmysql.models

import com.google.gson.annotations.SerializedName

data class Computer(
    @SerializedName("id") val id: Int,
    @SerializedName("name")  var name: String,
    @SerializedName("price")  var price: Double,
    @SerializedName("brand")  var brand: String,
    @SerializedName("description")  var description: String
)