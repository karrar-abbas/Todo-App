package com.example.todo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.TaskItemBinding
import com.example.todo.db.Task
import com.example.todo.newTaskSheet
import com.example.todo.updateTaskStatus

class TaskAdapter(var list:List<Task>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    var onItemClick : ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
       return TaskViewHolder(view)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask=list[position]
        holder.binding.apply {
            taskTitle.text=currentTask.title
            taskContent.text=currentTask.desc
            when(currentTask.status){
                1->taskStatus.text="In Progress"
                2->taskStatus.text="Done"
                else -> taskStatus.text="Todo"
            }
            taskDate.text=currentTask.date
            root.setOnClickListener {
                onItemClick?.invoke(currentTask.id)
            }
        }
    }
    override fun getItemCount()=list.size
    class TaskViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem) {
        val binding= TaskItemBinding.bind(viewItem)
    }

    fun setData(newList:List<Task>){
        val diffResult=DiffUtil.calculateDiff(TaskDiffUtil(list,newList))
        list=newList
        diffResult.dispatchUpdatesTo(this)
    }
}