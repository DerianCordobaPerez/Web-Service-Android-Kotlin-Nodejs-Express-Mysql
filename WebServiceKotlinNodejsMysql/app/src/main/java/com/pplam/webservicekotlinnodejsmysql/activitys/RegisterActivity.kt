package com.pplam.webservicekotlinnodejsmysql.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pplam.webservicekotlinnodejsmysql.MainActivity
import com.pplam.webservicekotlinnodejsmysql.R
import com.pplam.webservicekotlinnodejsmysql.helpers.Validations
import com.pplam.webservicekotlinnodejsmysql.models.Service
import com.pplam.webservicekotlinnodejsmysql.models.SharedPreferenceManager
import com.pplam.webservicekotlinnodejsmysql.models.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private var etUserName: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var tvLogin: TextView? = null
    private var btnRegister: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if(SharedPreferenceManager.getInstance(this).isLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        this.etUserName = findViewById(R.id.etUserName)
        this.etEmail = findViewById(R.id.etEmail)
        this.etPassword = findViewById(R.id.etPassword)
        this.tvLogin = findViewById(R.id.tvLogin)
        this.btnRegister = findViewById(R.id.btnRegister)

        this.tvLogin?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        this.btnRegister?.setOnClickListener {
            this.signUp()
        }
    }

    private fun signUp() {

        if(!Validations.validationEditText(this, listOf(this.etUserName!!, this.etEmail!!, this.etPassword!!))) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show()
            return
        }

        GlobalScope.launch {
            Service.signup(this@RegisterActivity, User(etUserName?.text.toString(), etEmail?.text.toString(), etPassword?.text.toString()))
        }
    }

}