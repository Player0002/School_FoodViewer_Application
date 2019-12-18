package com.players.schoolfoodviewer.data.livedata.school

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.players.schoolfoodviewer.data.search.SearchSch1

class SchoolData : ViewModel(){
    val food : MutableLiveData<SearchSch1> = MutableLiveData()

    fun select(item : SearchSch1){
        food.value = item
    }
    fun getSelected() : LiveData<SearchSch1> {
        return food
    }
}