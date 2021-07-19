package com.pplam.webservicekotlinnodejsmysql.helpers

import android.content.Context
import android.os.Vibrator
import android.text.TextUtils
import android.widget.EditText

object Validations {

    fun validationEditText(context: Context, array: List<EditText>): Boolean {
        for(i in array.indices) {
            if(TextUtils.isEmpty(array[i].text.trim()) || array[i].text.toString() == ".") {
                (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(150)
                array[i].error = "This fields are required"
                array[i].requestFocus()
                return false
            }
        }
        return true
    }

}