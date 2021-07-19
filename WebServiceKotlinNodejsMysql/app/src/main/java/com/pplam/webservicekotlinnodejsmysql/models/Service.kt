package com.pplam.webservicekotlinnodejsmysql.models

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.pplam.webservicekotlinnodejsmysql.MainActivity
import com.pplam.webservicekotlinnodejsmysql.adapters.ComputerAdapter
import com.pplam.webservicekotlinnodejsmysql.helpers.PublicData
import com.pplam.webservicekotlinnodejsmysql.interfaces.ApiComputer
import com.pplam.webservicekotlinnodejsmysql.interfaces.ApiUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object Service {

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.27:4000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun saveComputers(context: Context, computer: Computer) {
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                try {
                    val response = getRetrofit().create(ApiComputer::class.java)
                        .addComputer(computer.name, computer.price, computer.brand, computer.description)

                    Log.e("Data", response.body().toString())
                    if(response.isSuccessful && response.body() != null)
                        Toast.makeText(context, "Computer created successfully", Toast.LENGTH_SHORT).show()
                    else
                        Log.e("Empty data", response.body()!!.message)
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

    suspend fun updateComputer(context: Context, computer: Computer) {
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                try {
                    val response = getRetrofit().create(ApiComputer::class.java)
                        .updateComputer(computer.id, computer.name, computer.price, computer.brand, computer.description)

                    Log.e("Data", response.body().toString())
                    if(response.isSuccessful && response.body() != null)
                        Toast.makeText(context, "Computer updated successfully", Toast.LENGTH_SHORT).show()
                    else
                        Log.e("Empty data", response.body()!!.message)
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

    suspend fun deleteComputer(context: Context, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                try {
                    val response = getRetrofit().create(ApiComputer::class.java)
                        .deleteComputer(id)

                    if(response.isSuccessful && response.body()!!.status == "success")
                        Toast.makeText(context, "Computer deleted successfully", Toast.LENGTH_SHORT).show()
                    else
                        Log.e("Empty data", response.body()!!.message)
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

    suspend fun getAllComputer(adapter: ComputerAdapter) {
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                try {
                    val response = getRetrofit().create(ApiComputer::class.java)
                        .getAllComputers()

                    Log.e("Data", response.body().toString())

                    if(response.isSuccessful && response.body() != null){
                        Log.e("Data", response.body()!!.computers.count().toString())
                        PublicData.computers = response.body()!!.computers
                        adapter.notifyDataSetChanged()
                        Log.e("Get computers", "Getting data")
                    } else
                        Log.e("Empty data", response.body()!!.message)
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

    suspend fun signup(context: Context, user: User) {
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                try {
                    val response = getRetrofit().create(ApiUser::class.java)
                        .signup(user.userName, user.email, user.password)

                    if(response.isSuccessful && response.body() != null) {
                        SharedPreferenceManager.getInstance(context).userLogin(user)
                        context.startActivity(Intent(context, MainActivity::class.java))
                        (context as Activity).finish()
                    } else
                        Toast.makeText(context, "The user could not be registered", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

    suspend fun signin(context: Context, user: User) {
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                try {
                    val response = getRetrofit().create(ApiUser::class.java)
                            .signin(user.userName, user.password)

                    if(response.isSuccessful && response.body() != null) {
                        user.email = response.body()!!.user.email
                        SharedPreferenceManager.getInstance(context).userLogin(user)
                        context.startActivity(Intent(context, MainActivity::class.java))
                        (context as Activity).finish()
                    } else
                        Toast.makeText(context, "The user could not be registered", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

}