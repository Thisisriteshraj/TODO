package com.example.todogreensoft.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ToDo::class], version = 2)
abstract class ToDoDataBase : RoomDatabase() {
    abstract fun toDoDAO(): ToDoDAO
}