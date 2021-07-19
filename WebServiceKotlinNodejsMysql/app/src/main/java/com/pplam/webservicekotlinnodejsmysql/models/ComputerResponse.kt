package com.pplam.webservicekotlinnodejsmysql.models

import com.google.gson.annotations.SerializedName

data class ComputerResponse(
    @SerializedName("status") var status: String,
    @SerializedName("computers") var computers: MutableList<Computer>,
    @SerializedName("message") var message: String
)