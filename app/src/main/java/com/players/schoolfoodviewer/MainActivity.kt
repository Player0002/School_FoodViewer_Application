package com.players.schoolfoodviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.players.schoolfoodviewer.data.livedata.school.SchoolData
import com.players.schoolfoodviewer.data.search.SearchSch1
import com.players.schoolfoodviewer.data.search.SearchSch2
import com.players.schoolfoodviewer.data.food.FoodDataIT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    var model : SchoolData =
        SchoolData()
    var recyclerView : RecyclerView? = null
    val observer : Observer<SearchSch1> = Observer {
        val adap = SchAdapter(this, it.data)
        recyclerView?.adapter = adap
        val layout : LinearLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layout
        recyclerView?.setHasFixedSize(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.school_list)
        val textview = findViewById<TextView>(R.id.school_name_tv)
        model = ViewModelProviders.of(this).get(SchoolData::class.java)
        model.getSelected().removeObserver(observer)
        model.getSelected().observe(this, observer)
        if(!App.prefs.text.isEmpty()) startActivity(Intent(applicationContext, FoodViewer::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
        findViewById<Button>(R.id.search_btn).setOnClickListener {
            if(textview.text.isEmpty()) Toast.makeText(this, "학교명을 입력해주세요!", Toast.LENGTH_LONG).show()
            else{
                val retrofit : Retrofit = Retrofit.Builder().baseUrl(getString(R.string.url)).addConverterFactory(
                    GsonConverterFactory.create())
                    .build()
                val itf : FoodDataIT = retrofit.create(
                    FoodDataIT::class.java)
                val date : LocalDateTime = LocalDateTime.now()

                itf.getSch(textview.text.toString()).enqueue(object : Callback<SearchSch1>{
                    override fun onFailure(call: Call<SearchSch1>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<SearchSch1>, response: Response<SearchSch1>) {
                        println(response.body()?.data?.get(0)?.name)
                        if(response.body()?.data != null) model.select(response.body()!!)
                        else
                        model.select(
                            SearchSch1(
                                true,
                                "NO DATA",
                                null,
                                arrayOf(
                                    SearchSch2(
                                        0,
                                        "다시한번 확인해주세요",
                                        "D0",
                                        "학교명을 찾을 수 없습니다.",
                                        "ㅇ",
                                        "ㅇㅇㅇ"
                                    )
                                )
                            )
                        )
                    }

                })
            }
        }
    }
}
