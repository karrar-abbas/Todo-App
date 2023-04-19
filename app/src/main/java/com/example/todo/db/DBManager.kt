package com.example.todo.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper

class DBManager(val context:Context) {
    private  var databaseHelper: SQLiteOpenHelper =TaskHelper(context)
    private var _tasks:MutableList<Task> =mutableListOf<Task>()
    private lateinit var  cursor:Cursor
    val tasks:List<Task>
        get() = _tasks.toList()
    fun readAllData() {
        cursor=databaseHelper.readableDatabase.rawQuery("SELECT * FROM ${DB.TABLE_NAME} ORDER BY ${DB.ID} DESC", arrayOf<String>())
        addData()
    }
    fun choiceTasksStatus(choice:Int){
        checkUserChoice(choice)
        addData()
    }
    private fun checkUserChoice(choice: Int) {
        cursor = if(choice==3) {
            databaseHelper.readableDatabase.rawQuery(
                "SELECT * FROM ${DB.TABLE_NAME} ORDER BY ${DB.ID} DESC",
                arrayOf<String>()
            )
        }else{
            databaseHelper.readableDatabase.rawQuery(
                "SELECT * FROM ${DB.TABLE_NAME} WHERE ${DB.STATUS} = ? ORDER BY ${DB.ID} DESC",
                arrayOf<String>(choice.toString())
            )
        }
    }
    private fun addData() {
        _tasks.clear()
        while (cursor.moveToNext()){
            val id=cursor.getInt(0)
            val title=cursor.getString(1)
            val desc=cursor.getString(2)
            val status=cursor.getInt(3)
            val date=cursor.getString(4)
            if(!_tasks.contains(Task(id,title,desc,status,date))){
                _tasks.add(Task(id,title,desc,status,date))
            }
        }
    }
}