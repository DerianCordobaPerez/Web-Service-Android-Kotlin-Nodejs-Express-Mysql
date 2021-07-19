package com.pplam.webservicekotlinnodejsmysql.interfaces

import com.pplam.webservicekotlinnodejsmysql.models.UserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
interface ApiUser {

    @FormUrlEncoded
    @POST("user/signup")
    suspend fun signup(
            @Field("userName")userName: String,
            @Field("email")email: String,
            @Field("password")password: String
    ): Response<UserResponse>

    @FormUrlEncoded
    @POST("user/signin")
    suspend fun signin(
            @Field("userName")userName: String,
            @Field("password")password: String
    ): Response<UserResponse>

}

