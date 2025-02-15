package com.example.todogreensoft.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = " ToDoTable")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    var number: Int,
    var title: String = "",
    var description: String = "",
    var idComplete: Boolean = false
) : Serializable
