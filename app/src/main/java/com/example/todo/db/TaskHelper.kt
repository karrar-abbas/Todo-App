package com.example.todo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskHelper(context:Context) :SQLiteOpenHelper(context, DBNAME,null, DBVERSION){
    companion object{
        private const val DBNAME="taskdb"
        private const val DBVERSION=1
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val sql="CREATE TABLE ${DB.TABLE_NAME} ("+
                "${DB.ID} INTEGER PRIMARY KEY,"+
                "${DB.TITLE} TEXT," +
                "${DB.DESCRIPTION} TEXT," +
                "${DB.STATUS} INTEGER,"+
                "${DB.DATE} TEXT"+
                ")"
        db?.execSQL("DELETE from ${DB.TABLE_NAME}");
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}