package com.example.todo

import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.db.DBManager
import com.example.todo.ui.TaskAdapter

class MainActivity : AppCompatActivity() {

   private lateinit var binding:ActivityMainBinding
    private lateinit var dbManager: DBManager
   private lateinit var adapter:TaskAdapter
   private var TasksChoice:Int=3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbManager=DBManager(applicationContext)
        addCallBacks()
        dbManager.readAllData()
        adapter=TaskAdapter(dbManager.tasks)
        adapter.onItemClick={
            val fr=updateTaskStatus()
            val mBundle = Bundle()
            mBundle.putInt("TaskId",it)
            fr.arguments = mBundle
            fr.show(supportFragmentManager,"updateTaskTag")
        }
        binding.rcTasks.adapter=adapter
    }
    private fun addCallBacks() {
        binding.apply {
            chip1.setOnCheckedChangeListener { compoundButton, b ->
                TasksChoice=3
                dbManager.readAllData()
                adapter.setData(dbManager.tasks)
            }
            chip2.setOnCheckedChangeListener { compoundButton, b ->
                TasksChoice=0
                dbManager.choiceTasksStatus(0)
                adapter.setData(dbManager.tasks)
            }
            chip3.setOnCheckedChangeListener { compoundButton, b ->
                TasksChoice=1
                dbManager.choiceTasksStatus(1)
                adapter.setData(dbManager.tasks)
            }
            chip4.setOnCheckedChangeListener { compoundButton, b ->
                TasksChoice=2
                dbManager.choiceTasksStatus(2)
                adapter.setData(dbManager.tasks)
            }
            addTask.setOnClickListener {
                newTaskSheet().show(supportFragmentManager,"newTaskTag")
            }
        }

    }
    fun notifyAdapter(){
        when(TasksChoice){
            0 -> dbManager.choiceTasksStatus(0)
            1 -> dbManager.choiceTasksStatus(1)
            2 -> dbManager.choiceTasksStatus(2)
            else -> dbManager.readAllData()
        }
        adapter.setData(dbManager.tasks)
    }



}