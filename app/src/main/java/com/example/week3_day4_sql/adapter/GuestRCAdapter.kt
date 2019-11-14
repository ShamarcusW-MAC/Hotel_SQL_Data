package com.example.week3_day4_sql.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week3_day4_sql.R
import com.example.week3_day4_sql.model.Guest
import com.example.week3_day4_sql.view.GuestActivity
import com.example.week3_day4_sql.view.MainActivity.Companion.roomList

class GuestRCAdapter(private  val guestList: List<Guest>):
        RecyclerView.Adapter<GuestRCAdapter.MyCustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.guest_list_layout, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        holder.apply {
            guestNameTextView.text = guestList[position].name
            roomTextView.text = guestList[position].room
            nightTextView.text = guestList[position].nights.toString()
        }
    }


    inner class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val guestNameTextView = itemView.findViewById<TextView>(R.id.guest_name_textview)
        val nightTextView = itemView.findViewById<TextView>(R.id.numberofnights_textview)
        val roomTextView = itemView.findViewById<TextView>(R.id.room_textview)
    }
}