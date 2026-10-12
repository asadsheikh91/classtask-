package com.example.myrvex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(ls: ArrayList<Contact>, c: MainActivity) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var ls= ls
    var c=c

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): MyViewHolder {
        var v= LayoutInflater.from(c).inflate(
            R.layout.row,
            null,
            true)
        return MyViewHolder(v)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text=ls.get(position).name
        holder.phno.text=ls.get(position).phno
    }

    override fun getItemCount(): Int {
        return ls.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name=itemView.findViewById<TextView>(R.id.name)
        var phno=itemView.findViewById<TextView>(R.id.phno)
    }
}