package com.example.week3_day4_sql.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.week3_day4_sql.R
import com.example.week3_day4_sql.adapter.RoomAdapter
import com.example.week3_day4_sql.model.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    companion object {
        var roomList: MutableList<Room> = mutableListOf()
        var room: String? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        roomList.add(Room("Room 101"))
        roomList.add(Room("Room 102"))
        roomList.add(Room("Room 103"))

        val newAdapter = RoomAdapter(roomList)

        room_listview.adapter = newAdapter

        room_listview.setOnItemClickListener(AdapterView.OnItemClickListener(){ parent, view, position, id ->

            Toast.makeText(this, roomList[position].roomnumber, Toast.LENGTH_SHORT).show()
            if(roomList[position].roomnumber == "Room 101")
            {
                room = roomList[position].roomnumber
                Glide.with(this)
                    .load(getString(R.string.hotel_room_1))
                    .into(room_imageView)
            }


            if(roomList[position].roomnumber == "Room 102")
            {
                room = roomList[position].roomnumber

                Glide.with(this)
                    .load(getString(R.string.hotel_room_2))
                    .into(room_imageView)
            }


            if(roomList[position].roomnumber == "Room 103")
            {
                room = roomList[position].roomnumber


                Glide.with(this)
                    .load(getString(R.string.hotel_room_3))
                    .into(room_imageView)
            }

        })
        select_button.setOnClickListener { _->
            var intent = Intent(this, GuestActivity::class.java)
            intent.putExtra("Room", room)
            startActivity(intent)

        }

//        room_listview.setOnItemClickListener { parent, view, position, id ->
//            Glide.with(this)
//                .load(getString(R.string.hotel_room_1))
//                .into(room_imageView)
//        }



//        roomList.add(0, roomList[0])
//        roomList.add(1, roomList[1])
//        roomList.add(2, roomList[2])



    }
}
