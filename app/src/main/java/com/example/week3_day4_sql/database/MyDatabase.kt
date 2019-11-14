package com.example.week3_day4_sql.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.week3_day4_sql.model.Guest

class MyDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION, null ){

    companion object{
        const val DATABASE_NAME = "my_guests.db"
        const val DATABASE_VERSION = 2
        const val TABLE_NAME = "Guests"
        const val GUEST_ID = "Guest_ID"
        const val COLUMN_NAME = "Name"
        const val COLUMN_ROOM = "Room"
        const val COLUMN_NIGHTS = "Number_of_Nights"


    }

    override fun onCreate(db: SQLiteDatabase) {
        val createStatement =
            "CREATE TABLE $TABLE_NAME ($GUEST_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_ROOM TEXT, $COLUMN_NIGHTS INTEGER)"
        db.execSQL(createStatement)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertGuest(newGuest: Guest) {
        val guestValues = ContentValues()
        guestValues.put(COLUMN_NAME, newGuest.name)
        guestValues.put(COLUMN_ROOM, newGuest.room)
        guestValues.put(COLUMN_NIGHTS, newGuest.nights.toString())
        val db = writableDatabase
        db.insert(TABLE_NAME, null, guestValues)
        db.close()
    }

    fun readAllGuests(): Cursor {
        val query = "SELECT * FROM $TABLE_NAME"
        val db = readableDatabase
        return db.rawQuery(query, null)
        Log.d("TAG",db.rawQuery(query, null).toString())
    }
}