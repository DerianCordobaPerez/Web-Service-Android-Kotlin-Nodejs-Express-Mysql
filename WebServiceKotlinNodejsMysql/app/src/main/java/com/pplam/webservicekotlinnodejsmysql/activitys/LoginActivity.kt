package com.pplam.webservicekotlinnodejsmysql.activitys

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.pplam.webservicekotlinnodejsmysql.MainActivity
import com.pplam.webservicekotlinnodejsmysql.R
import com.pplam.webservicekotlinnodejsmysql.helpers.Validations
import com.pplam.webservicekotlinnodejsmysql.models.Service
import com.pplam.webservicekotlinnodejsmysql.models.SharedPreferenceManager
import com.pplam.webservicekotlinnodejsmysql.models.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    private var etUserName: EditText? = null
    private var etPassword: EditText? = null
    private var tvRegister: TextView? = null
    private var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(SharedPreferenceManager.getInstance(this).isLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        this.title = "Login / WebService"

        this.etUserName = findViewById(R.id.etUserName)
        this.etPassword = findViewById(R.id.etPassword)
        this.progressBar = findViewById(R.id.progressBar)
        this.btnLogin = findViewById(R.id.btnLogin)
        this.tvRegister = findViewById(R.id.tvRegisterNewAccount)

        this.btnLogin?.setOnClickListener {
            this.userLogin()
        }

        this.tvRegister?.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun userLogin() {

        if(!Validations.validationEditText(this, listOf(this.etUserName!!, this.etPassword!!))) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show()
            return
        }

        GlobalScope.launch {
            Service.signin(this@LoginActivity, User(etUserName?.text.toString(), "", etPassword?.text.toString()))
        }

    }

}