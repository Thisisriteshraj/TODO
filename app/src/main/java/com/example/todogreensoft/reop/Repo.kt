package com.example.todogreensoft.reop

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todogreensoft.roomDB.RoomInstance
import com.example.todogreensoft.roomDB.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Repo {
    fun loadData(context: Context) :MutableLiveData<List<ToDo>>{
         val _todoList = MutableLiveData<List<ToDo>>()


        CoroutineScope(Dispatchers.IO).launch {
            val list = RoomInstance.getRoomInstance(context).toDoDAO().getAllToDoList()
            _todoList.postValue(list)
        }

        return _todoList
    }
}