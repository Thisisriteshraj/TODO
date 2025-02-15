package com.example.todogreensoft.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDAO {

    @Query("SELECT * FROM  ` ToDoTable`")
    fun getAllToDoList(): List<ToDo>

    @Insert
    suspend fun insertToDo(user: ToDo)

    @Delete
    suspend fun deleteToDo(user: ToDo)

    @Query("DELETE FROM ` ToDoTable`")
    suspend fun deleteToDo()


    @Query("UPDATE ` ToDoTable` SET title = :title, description = :description WHERE number = :number")
    suspend fun updateToDoByNumber(number: Int, title: String, description: String)


    @Query("UPDATE ` todotable` SET idComplete = :isComplete WHERE number = :number")
    suspend fun updateIsComplete(number: Int, isComplete: Boolean)

}


