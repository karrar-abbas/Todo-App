package com.example.todo

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.databinding.FragmentNewTaskSheetBinding
import com.example.todo.databinding.FragmentUpdateTaskStatusBinding
import com.example.todo.db.DB
import com.example.todo.db.DBManager
import com.example.todo.db.TaskHelper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class updateTaskStatus : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentUpdateTaskStatusBinding
    private lateinit var datebaseHelper: SQLiteOpenHelper
    private var choice:Int=0
    private  var TaskId:Int=0

    override fun onStart() {
        super.onStart()
        arguments?.let {
            TaskId=it.getInt("TaskId")
            Log.d("todoapp","id $TaskId")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datebaseHelper= TaskHelper(requireNotNull(this.activity).application)
        addCallBacks()


    }
    private fun addCallBacks() {
        binding.apply {
            chipTodo.setOnCheckedChangeListener { compoundButton, b ->
                choice=0
            }
            chipProgress.setOnCheckedChangeListener { compoundButton, b ->
                choice=1
            }
            chipDone.setOnCheckedChangeListener { compoundButton, b ->
                choice=2
            }
            updateTask.setOnClickListener {
                updateTask()
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentUpdateTaskStatusBinding.inflate(inflater,container,false)
        return binding.root
    }
    private fun updateTask() {
        val newEntry= ContentValues().apply { put(DB.STATUS,choice) }
        datebaseHelper.writableDatabase.update(DB.TABLE_NAME,newEntry,"${DB.ID}="+TaskId,null)
        datebaseHelper.close()
        val dbManager= DBManager(requireNotNull(this.activity).application)
        (activity as MainActivity?)!!.notifyAdapter()
        dismiss()
    }
}