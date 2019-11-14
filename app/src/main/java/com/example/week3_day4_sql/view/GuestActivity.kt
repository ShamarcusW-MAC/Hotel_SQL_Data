package com.example.week3_day4_sql.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3_day4_sql.R
import com.example.week3_day4_sql.adapter.GuestRCAdapter
import com.example.week3_day4_sql.database.MyDatabase
import com.example.week3_day4_sql.model.Guest
import kotlinx.android.synthetic.main.activity_guest.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.room_textview
import kotlinx.android.synthetic.main.guest_list_layout.*
import kotlinx.android.synthetic.main.room_item_layout.*

class GuestActivity : AppCompatActivity() {

    companion object {
        var guestList = mutableListOf<Guest>()
        lateinit var myDatabase: MyDatabase
        var room:String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)


        var intent = getIntent()
        myDatabase = MyDatabase(this)


        room = intent.getStringExtra("Room")
        booked_textview.text = room + " will now be assigned to:"

        submit_button.setOnClickListener { _->

            saveToDatabase()

        }



    }

    override fun onResume() {
        super.onResume()
        readFromDatabase()
    }

    private fun saveToDatabase() {
        val guestName = name_edittext.text.toString()
        val guestRoom = room.toString()
        val guestNights = night_edittext.text.toString()
        val newGuest = Guest(guestName, guestRoom, Integer.parseInt(guestNights))
        myDatabase.insertGuest(newGuest)
        Toast.makeText(this, "Guest added to database.", Toast.LENGTH_SHORT).show()
        clearFields()
        readFromDatabase()
    }

    private fun clearFields() {
        name_edittext.text.clear()
        night_edittext.text.clear()
    }

    private fun readFromDatabase() {
        guestList = mutableListOf()

        val cursor = myDatabase.readAllGuests()
        cursor.moveToFirst()

        if (cursor.count > 0) {
            val guest = Guest(
                cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_ROOM)),
                cursor.getInt(cursor.getColumnIndex(MyDatabase.COLUMN_NIGHTS)))
            guestList.add(guest)
        }
        while (cursor.moveToNext()) {
            val guestName = cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_NAME))
            val guestRoom = cursor.getString(cursor.getColumnIndex(MyDatabase.COLUMN_ROOM))
            val guestNights = cursor.getInt(cursor.getColumnIndex(MyDatabase.COLUMN_NIGHTS))
            val readGuest = Guest(guestName, guestRoom, guestNights)
            guestList.add(readGuest)

//            Toast.makeText(this,readGuest.name,Toast.LENGTH_SHORT).show()
        }


        displayUsers()
    }

    private fun displayUsers() {

        val recyclerAdapter = GuestRCAdapter(guestList)
        guest_listview.adapter = recyclerAdapter
        val layoutMgr = LinearLayoutManager(this)
        guest_listview.layoutManager = layoutMgr
        recyclerAdapter.notifyDataSetChanged()

    }
}
