package com.example.todo

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.databinding.FragmentNewTaskSheetBinding
import com.example.todo.db.DB
import com.example.todo.db.DBManager
import com.example.todo.db.Task
import com.example.todo.db.TaskHelper
import com.example.todo.ui.TaskAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar


class newTaskSheet : BottomSheetDialogFragment(){

    private lateinit var binding:FragmentNewTaskSheetBinding
    private lateinit var bindRc:ActivityMainBinding
    private lateinit var datebaseHelper: SQLiteOpenHelper



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datebaseHelper= TaskHelper(requireNotNull(this.activity).application)
        binding.saveTask.setOnClickListener {
            addNewTask()
        }
        Log.d("todoapp","onviewcreated")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        bindRc= ActivityMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun addNewTask() {
        val title=binding.taskTitle.text.toString()
        val desc=binding.taskDesc.text.toString()
        val status=0
        val date=getdate()

        insertData(title,desc,status,date)
        binding.taskTitle.setText("")
        binding.taskDesc.setText("")
        Log.d("todoapp","insidefun")
        val dbManager=DBManager(requireNotNull(this.activity).application)
        dbManager.readData()
        (activity as MainActivity?)!!.notifyAdapter()


        dismiss()
    }

    private fun insertData(title:String,desc:String,status:Int,date:String) {
        val newEntry= ContentValues().apply {
            put(DB.TITLE,title)
            put(DB.DESCRIPTION,desc)
            put(DB.STATUS,status)
            put(DB.DATE,date)
        }
        datebaseHelper.writableDatabase.insert(DB.TABLE_NAME,null,newEntry)
    }

    private fun getdate():String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return formatter.format(time)
    }


}