package com.pplam.webservicekotlinnodejsmysql.models

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.pplam.webservicekotlinnodejsmysql.activitys.LoginActivity

class SharedPreferenceManager private constructor(context: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getString("userName", null) != null
        }

    val user: User
        get() {
            val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                    sharedPreferences!!.getString("userName", null).toString(),
                    sharedPreferences.getString("email", null).toString(),
                    sharedPreferences.getString("password", null).toString()
            )
        }

    init {
        ctx = context
    }

    fun userLogin(user: User) {
        val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        editor?.putString("userName", user.userName)
        editor?.putString("email", user.email)
        editor?.putString("password", user.password)
        editor?.apply()
    }

    fun logout() {
        val sharedPreferences = ctx?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        editor?.clear()
        editor?.apply()

        ctx?.startActivity(Intent(ctx, LoginActivity::class.java))
    }


    companion object {
        private const val SHARED_PREF_NAME = "WebServiceLogin"

        @SuppressLint("StaticFieldLeak")
        private var ctx: Context? = null

        @SuppressLint("StaticFieldLeak")
        private var instance: SharedPreferenceManager? = null

        @Synchronized
        fun getInstance(context: Context): SharedPreferenceManager {
            if(instance == null)
                instance = SharedPreferenceManager(context)
            return instance as SharedPreferenceManager
        }
    }
}