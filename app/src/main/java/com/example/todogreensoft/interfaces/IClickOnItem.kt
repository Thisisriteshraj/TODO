package com.example.todogreensoft.interfaces

import com.example.todogreensoft.roomDB.ToDo

interface IClickOnItem {
    fun onItemClickOfToDoItem(todo: ToDo)
}