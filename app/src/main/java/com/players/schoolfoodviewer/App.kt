package com.players.schoolfoodviewer

import android.app.Application
import com.players.schoolfoodviewer.data.livedata.SharedPreferences

class App : Application() {
    companion object{
        lateinit var prefs : SharedPreferences
    }

    override fun onCreate() {
        prefs = SharedPreferences(
            applicationContext
        )
        super.onCreate()
    }
}