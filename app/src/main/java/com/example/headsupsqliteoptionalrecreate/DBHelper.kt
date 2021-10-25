package com.example.headsupsqliteoptionalrecreate

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context):SQLiteOpenHelper(context,"Celebrities",null,1) {
   val dbWrite:SQLiteDatabase=writableDatabase
   val dbReade:SQLiteDatabase=readableDatabase
    override fun onCreate(p0: SQLiteDatabase?) {

        if(p0!=null){
            p0.execSQL("create table Celebrities(id integer primary key autoincrement,name text,taboo1 text,taboo2 text,taboo3 text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) { }
    fun addData(name:String,taboo1:String, taboo2:String, taboo3:String): Long
    {
        val contentValues=ContentValues()
        contentValues.put("name",name)
        contentValues.put("taboo1",taboo1)
        contentValues.put("taboo2",taboo2)
        contentValues.put("taboo3",taboo3)
        return dbWrite.insert("Celebrities",null,contentValues)
    }

    fun getData():ArrayList<Celebrity>
    {
        val celebrities=ArrayList<Celebrity>()
        val cursor:Cursor=dbReade.query("Celebrities",null,null,null,null,null,null)
        cursor.moveToFirst()
        while (cursor.moveToNext())
        {
            val id=cursor.getInt(cursor.getColumnIndex("id"))
            val name=cursor.getString(cursor.getColumnIndex("name"))
            val taboo1=cursor.getString(cursor.getColumnIndex("taboo1"))
            val taboo2=cursor.getString(cursor.getColumnIndex("taboo2"))
            val taboo3=cursor.getString(cursor.getColumnIndex("taboo3"))
            celebrities.add(Celebrity(id,name,taboo1,taboo2,taboo3))
        }
       val hh=celebrities

        cursor.close()
        return celebrities

    }
}