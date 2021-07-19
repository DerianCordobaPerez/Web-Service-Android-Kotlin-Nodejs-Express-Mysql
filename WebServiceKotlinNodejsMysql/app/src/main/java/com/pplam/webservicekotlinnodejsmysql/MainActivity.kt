package com.pplam.webservicekotlinnodejsmysql

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pplam.webservicekotlinnodejsmysql.activitys.AddEditComputerActivity
import com.pplam.webservicekotlinnodejsmysql.activitys.LoginActivity
import com.pplam.webservicekotlinnodejsmysql.adapters.ComputerAdapter
import com.pplam.webservicekotlinnodejsmysql.models.Service
import com.pplam.webservicekotlinnodejsmysql.models.SharedPreferenceManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: ComputerAdapter? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(findViewById(R.id.btnOrderButton))

        if(SharedPreferenceManager.getInstance(this).isLoggedIn) {
            this.title = "Web Service / Main Panel"

            this.recyclerView = findViewById(R.id.recyclerViewLayout)

            this.adapter = ComputerAdapter()

            this.recyclerView?.setHasFixedSize(true)
            this.recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            this.updateView()

            findViewById<TextView>(R.id.tvUserLoggedIn).text = "Username: ${SharedPreferenceManager.getInstance(this).user.userName} / Email: ${SharedPreferenceManager.getInstance(this).user.email}"
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.general_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuAdd -> {
                startActivity(Intent(this, AddEditComputerActivity::class.java))
                true
            }

            R.id.menuLogout -> {
                SharedPreferenceManager.getInstance(this).logout()
                this.finish()
                true
            }

            R.id.menuExit -> {
                this.finish()
                true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.contextMenuOrderId -> {

                true
            }

            R.id.contextMenuOrderName -> {

                true
            }

            R.id.contextMenuOrderPrice -> {

                true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        this.updateView()
    }

    private fun updateView() {
        this.recyclerView?.adapter = this.adapter
        GlobalScope.launch {
            Service.getAllComputer(adapter = adapter!!)
        }
    }

}