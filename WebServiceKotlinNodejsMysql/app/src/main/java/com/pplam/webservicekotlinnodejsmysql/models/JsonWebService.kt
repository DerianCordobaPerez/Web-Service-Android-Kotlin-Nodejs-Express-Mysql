package com.pplam.webservicekotlinnodejsmysql.models

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

open class JsonWebService {

    @WorkerThread
    fun getJson(url: String): JSONArray? {

        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection

        connection.apply {
            connectTimeout = 10000
            requestMethod = "GET"
            doInput = true
        }

        if(connection.responseCode != HttpURLConnection.HTTP_OK)
            return null

        val streamReader = InputStreamReader(connection.inputStream)
        var text: String

        streamReader.use {
            text = it.readText()
        }

        return JSONArray(text)
    }

    fun sendJson(url: String, param: JSONObject): JSONObject? {
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection

        connection.doInput = true
        connection.doOutput = true
        connection.useCaches = false
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")
        connection.requestMethod = "POST"
        connection.connect()

        val bufferWrite = BufferedWriter(OutputStreamWriter(connection.outputStream, "UTF-8"))
        bufferWrite.write(param.toString())
        bufferWrite.flush()
        bufferWrite.close()

        val stringBuilder = StringBuilder()
        if(connection.responseCode == HttpURLConnection.HTTP_OK) {
            val buffer = BufferedReader(InputStreamReader(BufferedInputStream(connection.inputStream)))
            while(true)
                stringBuilder.append(buffer.readLine() ?: break)

            return JSONObject(stringBuilder.toString())
        }
        return null
    }
}