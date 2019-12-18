package com.players.schoolfoodviewer.data.livedata

import android.content.Context

class SharedPreferences(context : Context){

    val NAME = "school"
    val TEXT = "null"
    val ADD = "null2"
    val prefs  = context.getSharedPreferences(NAME, 0)

    var text : String
        get() = prefs.getString(TEXT, "")?:""
        set(value) = prefs.edit().putString(TEXT, value).apply()
    var address : String
        get() = prefs.getString(ADD, "") ?: ""
        set(value) = prefs.edit().putString(ADD, value).apply()
}