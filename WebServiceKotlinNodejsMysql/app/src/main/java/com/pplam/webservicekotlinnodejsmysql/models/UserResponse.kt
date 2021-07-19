package com.pplam.webservicekotlinnodejsmysql.models

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("status") var status: String,
    @SerializedName("user") var user: User,
    @SerializedName("message") var message: String
)