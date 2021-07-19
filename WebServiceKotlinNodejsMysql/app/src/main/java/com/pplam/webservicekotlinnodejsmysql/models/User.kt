package com.pplam.webservicekotlinnodejsmysql.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userName")  var userName: String,
    @SerializedName("email")  var email: String,
    @SerializedName("password")  var password: String
)