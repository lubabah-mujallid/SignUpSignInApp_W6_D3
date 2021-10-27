package com.example.signupsigninapp_w6_d3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database( context: Context?) : SQLiteOpenHelper(context, "userDetails.db", null, 1) {
    val sqLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, Name text, Mobile text, Location text, Password text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(sqLiteDatabase)
    }

    fun saveData(user: User){
        val cv = ContentValues()
        cv.put("Name", user.name)
        cv.put("Mobile", user.mobile)
        cv.put("Location", user.location)
        cv.put("Password", user.password)
        sqLiteDatabase.insert("users", null, cv)
    }

    @SuppressLint("Range")
    fun retrieveRowByName(name: String): String{
        var c: Cursor = sqLiteDatabase.query("users" , null, "Name=?",
            arrayOf(name),null, null, null)
        if (c.count < 1) {
            println("DataBase not found")
        }
        c.moveToFirst()
        var loc = c.getString(c.getColumnIndex("Location"))
        return (loc)
    }

    @SuppressLint("Range")
    fun isRealUser(s1: String, s2: String): Any{
        var c: Cursor = sqLiteDatabase.query("users" , null, "Mobile=?",
            arrayOf(s1),null, null, null)
        if (c.count < 1) {
            return -1 //name not found
        }
        else{ //found name
            c.moveToFirst()
            var name = c.getString(c.getColumnIndex("Name"))
            var pass = c.getString(c.getColumnIndex("Password"))
            if (pass == s2) { //check if password is right
                return name//password correct
            }
            else{
                return 0 //password wrong
            }
        }
    }

}