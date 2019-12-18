package com.players.schoolfoodviewer.ui.dinner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.players.schoolfoodviewer.data.livedata.food.FoodData
import com.players.schoolfoodviewer.R
import com.players.schoolfoodviewer.data.food.Food

class DinnerFragment : Fragment() {

    var recyclerView : RecyclerView? = null
    var model : FoodData =
        FoodData()
    val observer : Observer<Food> = Observer<Food> {
        val arr = it.dinner
        if(arr.get(0).isEmpty()) arr.set(0,"급식 정보가 없습니다.")
        val adapters : DinnerAdapter = DinnerAdapter(context!!,arr)
        recyclerView?.adapter = adapters
        val layout : LinearLayoutManager = LinearLayoutManager(context!!)
        recyclerView?.layoutManager = layout
        recyclerView?.setHasFixedSize(true)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        recyclerView = root.findViewById(R.id.dinner)

        model = ViewModelProviders.of(activity!!).get(FoodData::class.java)
        model.getSelected().removeObserver(observer)
        model.getSelected().observe(this, observer)
        return root
    }

}