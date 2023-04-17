package com.example.todo.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBManager(val context:Context) {
    private lateinit var datebaseHelper: SQLiteOpenHelper
    private lateinit var taskList:MutableList<Task>
    val tasks:List<Task>
        get() = taskList.toList()

    fun readData() {
        datebaseHelper=TaskHelper(context)

        val cursor=datebaseHelper.readableDatabase.rawQuery("SELECT * FROM ${DB.TABLE_NAME} ORDER BY ${DB.ID} DESC", arrayOf<String>())

        taskList=mutableListOf<Task>()

        while (cursor.moveToNext()){
            val id=cursor.getInt(0)
            val title=cursor.getString(1)
            val desc=cursor.getString(2)
            val status=cursor.getInt(3)
            val date=cursor.getString(4)
            taskList.add(Task(id,title,desc,status,date))


        }
    }
    fun choiceStatus(choice:Int){
        datebaseHelper=TaskHelper(context)
        val cursor:Cursor
        if(choice==3) {
            cursor = datebaseHelper.readableDatabase.rawQuery(
                "SELECT * FROM ${DB.TABLE_NAME} ORDER BY ${DB.ID} DESC",
                arrayOf<String>()
            )
        }else{
            cursor = datebaseHelper.readableDatabase.rawQuery(
                "SELECT * FROM ${DB.TABLE_NAME} WHERE ${DB.STATUS} = ? ORDER BY ${DB.ID} DESC",
                arrayOf<String>(choice.toString())
            )
        }
        taskList=mutableListOf<Task>()

        while (cursor.moveToNext()){
            val id=cursor.getInt(0)
            val title=cursor.getString(1)
            val desc=cursor.getString(2)
            val status=cursor.getInt(3)
            val date=cursor.getString(4)
            taskList.add(Task(id,title,desc,status,date))


        }
    }
}