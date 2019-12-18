package com.players.schoolfoodviewer.data.livedata.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.players.schoolfoodviewer.data.food.Food

class FoodData : ViewModel(){
    val food : MutableLiveData<Food> = MutableLiveData()

    fun select(item : Food){
        food.value = item
    }
    fun getSelected() : LiveData<Food>{
        return food
    }

}