package com.players.schoolfoodviewer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.players.schoolfoodviewer.data.food.Food
import com.players.schoolfoodviewer.data.food.FoodDataIT
import com.players.schoolfoodviewer.data.livedata.food.FoodData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FoodViewer : AppCompatActivity() {

    var model : FoodData =
        FoodData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_viewer)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val schoolName = App.prefs.text
        val schoolAddress = App.prefs.address
        val navController = findNavController(R.id.nav_host_fragment)
        val currentFood = findViewById<TextView>(R.id.currentFood)
        findViewById<TextView>(R.id.schoolName).setText(schoolName)
        navController.addOnDestinationChangedListener{ _, dis, _ ->
            when{
                dis.id == R.id.navigation_morning-> currentFood.setText("아침")
                dis.id == R.id.navigation_lunch  -> currentFood.setText("점심")
                dis.id == R.id.navigation_dinner -> currentFood.setText("저녁")
            }
        }
        findViewById<Button>(R.id.changeSchool).setOnClickListener {
            App.prefs.text = ""
            startActivity(Intent(this, MainActivity::class.java))
        }
        navView.setupWithNavController(navController)
        model = ViewModelProviders.of(this).get(FoodData::class.java)
        model.select(
            Food(
                arrayOf("데이터를 로딩중입니다.."),
                arrayOf("데이터를 로딩중입니다.."),
                arrayOf("데이터를 로딩중입니다..")
            )
        )
        val retrofit : Retrofit = Retrofit.Builder().baseUrl(getString(R.string.url)).addConverterFactory(
            GsonConverterFactory.create())
            .build()
        val itf : FoodDataIT = retrofit.create(
            FoodDataIT::class.java)
        val date : LocalDateTime = LocalDateTime.now()

        itf.getFood(schoolName, date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")), schoolAddress).enqueue(object : Callback<Food> {
            override fun onFailure(call: Call<Food>, t: Throwable) {
                println(call.request())
            }
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                model.select(response.body()!!)
            }

        })
    }
}
