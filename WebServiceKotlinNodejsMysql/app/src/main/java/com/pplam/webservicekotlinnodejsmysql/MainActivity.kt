package com.pplam.webservicekotlinnodejsmysql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pplam.webservicekotlinnodejsmysql.adapters.ComputerAdapter
import com.pplam.webservicekotlinnodejsmysql.models.ComputersJsonWebService

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: ComputerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(findViewById(R.id.btnOrderButton))

        this.recyclerView = findViewById(R.id.recyclerLayout)

        Thread {
            this.adapter = ComputerAdapter(ComputersJsonWebService().getComputers())
        }.start()

        this.recyclerView?.adapter = adapter
        this.recyclerView?.setHasFixedSize(true)
        this.recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.general_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuAdd -> {

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

}