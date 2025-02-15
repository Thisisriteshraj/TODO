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


//package com.happybirthdaygreetings.birthdayvideomaker.reminderTools.room.room
//
//import android.content.Context
//import androidx.room.Room
//object RoomDatabaseInstance {
//    @Volatile
//    private var INSTANCE: UserDataBase? = null
//
//    fun getRoomInstance(context: Context): UserDataBase {
//        if (INSTANCE == null) {
//            synchronized(this) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        UserDataBase::class.java,
//                        "USER"
//                    )
//                        .fallbackToDestructiveMigration() // WARNING: This deletes existing data
//                        .build()
//                }
//            }
//        }
//        return INSTANCE!!
//    }
//
//}