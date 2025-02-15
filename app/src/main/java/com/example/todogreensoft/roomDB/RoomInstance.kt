package com.example.todogreensoft.roomDB

import android.content.Context
import androidx.room.Room

object RoomInstance {
        @Volatile
    private var INSTANCE: ToDoDataBase? = null
    fun getRoomInstance(context: Context): ToDoDataBase {
        if (INSTANCE == null) {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDataBase::class.java,
                        "USER"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
        return INSTANCE!!
    }
}

