package com.players.schoolfoodviewer.data.food

import com.players.schoolfoodviewer.data.search.SearchSch1
import retrofit2.Call
import retrofit2.http.*

interface FoodDataIT {
    @GET("/router/result")
    fun getFood(@Query("name") name : String, @Query("when") date : String, @Query("address") address : String) : Call<Food>
    @GET("/router/search")
    fun getSch(@Query("name") name : String) : Call<SearchSch1>
}