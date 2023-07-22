package com.example.myapplication.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.NonNull
import com.example.myapplication.item.HealthData
import com.example.myapplication.itemview.ItemFactory


class ListViewAdapter(@NonNull context: Context, itemType: Int): BaseAdapter() {
    private var healthDataList = mutableListOf<HealthData>()
    private val context = context
    private val itemType = itemType


    fun setData(list: List<HealthData>) {
        healthDataList.clear()
        healthDataList.addAll(list)
    }

    override fun getCount(): Int {
        return healthDataList.size
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var rootView = convertView
        val data = getItem(position)


        rootView = ItemFactory.getItem(context, itemType, data as HealthData)

        return rootView
    }

    override fun getItem(position: Int): Any {
        return healthDataList[position]
    }

}