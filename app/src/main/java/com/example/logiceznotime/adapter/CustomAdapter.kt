package com.example.logiceznotime.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.logiceznotime.R
import com.example.logiceznotime.model.CoinModel

internal class CustomAdapter(private var itemsList: MutableList<CoinModel>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var   usdTextView:TextView = view.findViewById(R.id.usdTextView_item)
        var  gbpTextView :TextView= view.findViewById(R.id.gbpTextView_item)
        var  eurTextView :TextView= view.findViewById(R.id.eurTextVieww_item)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.usdTextView.text =   item.bpi.USD.code + ": " + item.bpi.USD.rate
        holder.gbpTextView.text =  item.bpi.GBP.code + ": " + item.bpi.GBP.rate
            holder.eurTextView.text =   item.bpi.EUR.code + ": " + item.bpi.EUR.rate
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}