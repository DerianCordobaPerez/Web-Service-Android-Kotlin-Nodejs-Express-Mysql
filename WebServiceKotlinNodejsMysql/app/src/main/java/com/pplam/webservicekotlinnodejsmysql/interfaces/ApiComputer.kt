package com.pplam.webservicekotlinnodejsmysql.interfaces

import com.pplam.webservicekotlinnodejsmysql.models.ComputerResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiComputer {

    @GET("computer/getAll")
    suspend fun getAllComputers(): Response<ComputerResponse>

    @FormUrlEncoded
    @POST("computer/add")
    suspend fun addComputer(@Field("name")name: String,
                            @Field("price")price: Double,
                            @Field("brand")brand: String,
                            @Field("description")description: String): Response<ComputerResponse>

    @FormUrlEncoded
    @POST("computer/update")
    suspend fun updateComputer(@Field("id")id: Int,
                               @Field("name")name: String,
                               @Field("price")price: Double,
                               @Field("brand")brand: String,
                               @Field("description")description: String): Response<ComputerResponse>

    @FormUrlEncoded
    @POST("computer/delete")
    suspend fun deleteComputer(@Field("id")id: Int): Response<ComputerResponse>

}