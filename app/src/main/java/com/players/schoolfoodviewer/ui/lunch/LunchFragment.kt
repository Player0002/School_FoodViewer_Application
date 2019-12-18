package com.players.schoolfoodviewer.ui.lunch

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

class LunchFragment : Fragment() {

    var recyclerView : RecyclerView? = null
    var model : FoodData =
        FoodData()
    val observer : Observer<Food> = Observer<Food> {
        val arr = it.lunch
        if(arr.get(0).isEmpty()) arr.set(0,"급식 정보가 없습니다.")
        val adapters : LunchAdapter = LunchAdapter(context!!,arr)
        recyclerView?.adapter = adapters
        val layout : LinearLayoutManager = LinearLayoutManager(context!!)
        recyclerView?.layoutManager = layout
        recyclerView?.setHasFixedSize(true)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerView = root.findViewById(R.id.lunch)

        model = ViewModelProviders.of(activity!!).get(FoodData::class.java)
        model.getSelected().removeObserver(observer)
        model.getSelected().observe(this, observer)
        return root
    }

}