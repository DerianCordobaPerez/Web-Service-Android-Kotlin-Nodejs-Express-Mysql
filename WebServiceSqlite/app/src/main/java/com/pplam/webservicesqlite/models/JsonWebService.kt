package com.pplam.webservicesqlite.models

import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

open class JsonWebService {

    fun getJson(url: String): JSONObject? {
        val json: String
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val buffer = BufferedReader(InputStreamReader(BufferedInputStream(connection.inputStream)))
        val stringBuilder = StringBuilder()

        if(connection.responseCode == HttpURLConnection.HTTP_OK) {
            getLineBuffer(buffer, stringBuilder)
            json = stringBuilder.toString()
            return JSONObject(json)
        }
        return null
    }

    fun sendJson(url: String, param: JSONObject): JSONObject? {
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection

        connection.doInput = true
        connection.doOutput = true
        connection.useCaches = false
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")
        connection.connect()

        val bufferWrite = BufferedWriter(OutputStreamWriter(connection.outputStream, "UTF-8"))
        bufferWrite.write(param.toString())
        bufferWrite.flush()
        bufferWrite.close()

        val stringBuilder = StringBuilder()
        if(connection.responseCode == HttpURLConnection.HTTP_OK) {
            val buffer = BufferedReader(InputStreamReader(BufferedInputStream(connection.inputStream)))
            getLineBuffer(buffer, stringBuilder)
            return JSONObject(stringBuilder.toString())
        }
        return null
    }

    private fun getLineBuffer(buffer: BufferedReader, stringBuilder: StringBuilder) {
        while(true) {
            val line = buffer.readLine() ?: break
            stringBuilder.append(line)
        }
    }
}