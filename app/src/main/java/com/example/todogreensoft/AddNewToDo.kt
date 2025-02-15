package com.example.todogreensoft

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todogreensoft.databinding.ActivityAddNewToDoBinding
import com.example.todogreensoft.roomDB.RoomInstance
import com.example.todogreensoft.roomDB.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNewToDo : AppCompatActivity() {


    private lateinit var todo: ToDo

    private lateinit var binding: ActivityAddNewToDoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewToDoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val pastActivity = intent.getStringExtra("pastActivity")
        when (pastActivity) {

            "Edit" -> {

                binding.deletebtn.visibility = View.VISIBLE
                binding.buttonisComplete.visibility = View.VISIBLE
                binding.materialToolbar3.title="Update Your To Do"


                val todo = intent.getSerializableExtra("todo") as? ToDo
                if (todo != null) {

                    binding.title.setText(todo.title)
                    binding.description.setText(todo.description)
                }


                binding.save.setOnClickListener {


                    CoroutineScope(Dispatchers.IO)
                        .launch {

                            if (todo != null) {
                                RoomInstance.getRoomInstance(this@AddNewToDo)
                                    .toDoDAO()
                                    .updateToDoByNumber(
                                        todo.number,
                                        binding.title.text.toString(),
                                        binding.description.text.toString()
                                    )
                            }

                            withContext(Dispatchers.Main)
                            {
                                onBackPressedDispatcher.onBackPressed()
                            }

                        }


                }

                binding.deletebtn.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (todo != null) {
                            RoomInstance.getRoomInstance(this@AddNewToDo)
                                .toDoDAO()
                                .deleteToDo(todo)
                        }

                        finish()
                    }
                }

                binding.buttonisComplete.setOnClickListener {

                    if (todo != null) {
                        val newIsComplete = !todo.idComplete

                        CoroutineScope(Dispatchers.IO).launch {
                            RoomInstance.getRoomInstance(this@AddNewToDo)
                                .toDoDAO()
                                .updateIsComplete(todo.number, newIsComplete)


                            withContext(Dispatchers.Main) {
                                todo.idComplete = newIsComplete
                                binding.buttonisComplete.text = if (newIsComplete) "Mark as Incomplete" else "Mark as Complete"
                            }
                        }
                    }

                }


            }

            "New" -> {
                binding.materialToolbar3.title="Create a new To Do"


                binding.save.setOnClickListener {
                    todo =
                        ToDo(0, binding.title.text.toString(), binding.description.text.toString())
                    CoroutineScope(Dispatchers.IO).launch {
                        RoomInstance.getRoomInstance(this@AddNewToDo).toDoDAO().insertToDo(todo)


                        finish()
                    }
                }
            }
        }


    }


}