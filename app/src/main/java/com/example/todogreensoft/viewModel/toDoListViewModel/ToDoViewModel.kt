package com.example.todogreensoft.viewModel.toDoListViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todogreensoft.roomDB.RoomInstance
import com.example.todogreensoft.roomDB.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel : ViewModel() {

    private val _todoList = MutableLiveData<ArrayList<ToDo>>()
    val todoList: LiveData<ArrayList<ToDo>> = _todoList

    fun loadData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = RoomInstance.getRoomInstance(context).toDoDAO().getAllToDoList()

            // Convert List<ToDo> to ArrayList<ToDo> before posting the value
            _todoList.postValue(ArrayList(list))
        }
    }
}

