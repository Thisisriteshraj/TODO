package com.example.todogreensoft.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todogreensoft.reop.Repo
import com.example.todogreensoft.roomDB.ToDo


class ToDoViewModel : ViewModel() {

    private val _todoList = MutableLiveData<ArrayList<ToDo>>()
    val todoList: LiveData<ArrayList<ToDo>> = _todoList

    fun getAllTODOList(context: Context) {
        Repo.loadData(context).observeForever { list ->
            _todoList.postValue(ArrayList(list))
        }
    }
}



