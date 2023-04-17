package com.example.todo.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.todo.db.Task

class TaskDiffUtil(val oldList:List<Task>,val newList:List<Task>):DiffUtil.Callback() {
    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return (oldList[oldItemPosition].date ==newList[newItemPosition].date &&
               oldList[oldItemPosition].status ==newList[newItemPosition].status &&
               oldList[oldItemPosition].title ==newList[newItemPosition].title)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].status == newList[newItemPosition].status
    }
}