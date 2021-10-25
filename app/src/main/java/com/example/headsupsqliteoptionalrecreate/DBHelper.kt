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
      if (cursor.moveToFirst()){
            var id=cursor.getInt(cursor.getColumnIndex("id"))
            var name=cursor.getString(cursor.getColumnIndex("name"))
            var taboo1=cursor.getString(cursor.getColumnIndex("taboo1"))
            var taboo2=cursor.getString(cursor.getColumnIndex("taboo2"))
            var taboo3=cursor.getString(cursor.getColumnIndex("taboo3"))
            celebrities.add(Celebrity(id,name,taboo1,taboo2,taboo3))
            while (cursor.moveToNext())
            {
                 id=cursor.getInt(cursor.getColumnIndex("id"))
                 name=cursor.getString(cursor.getColumnIndex("name"))
                 taboo1=cursor.getString(cursor.getColumnIndex("taboo1"))
                 taboo2=cursor.getString(cursor.getColumnIndex("taboo2"))
                 taboo3=cursor.getString(cursor.getColumnIndex("taboo3"))
                celebrities.add(Celebrity(id,name,taboo1,taboo2,taboo3))
            }
        }
        cursor.close()
        return celebrities

    }
}
