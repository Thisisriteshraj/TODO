package com.example.todogreensoft.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.todogreensoft.roomDB.RoomInstance
import com.example.todogreensoft.roomDB.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewPeopleViewModel:ViewModel() {

    fun insertNewToDo(todo:ToDo,context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            RoomInstance.getRoomInstance(context).toDoDAO().insertToDo(todo)



        }


    }


    fun updateTick(todo:ToDo,newIsComplete:Boolean,context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            RoomInstance.getRoomInstance(context)
                .toDoDAO()
                .updateIsComplete(todo.number, newIsComplete)


//            withContext(Dispatchers.Main) {
//                todo.idComplete = newIsComplete
//                binding.buttonisComplete.text = if (newIsComplete) "Mark as Incomplete" else "Mark as Complete"
//            }
        }
    }


    fun deleteTODo(todo:ToDo,context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            if (todo != null) {
                RoomInstance.getRoomInstance(context)
                    .toDoDAO()
                    .deleteToDo(todo)
            }


        }
    }


    fun updateExistingToDo(todo: ToDo,context: Context){
        CoroutineScope(Dispatchers.IO)
            .launch {

                if (todo != null) {
                    RoomInstance.getRoomInstance(context)
                        .toDoDAO()
                        .updateToDoByNumber(
                            todo.number,
                            todo.title,
                            todo.description
                        )
                }



            }

    }



}