package com.example.todo.db

data class Task(
    val id:Int,
    val title:String,
    val desc:String,
    val status:Int,
    val date:String
)
