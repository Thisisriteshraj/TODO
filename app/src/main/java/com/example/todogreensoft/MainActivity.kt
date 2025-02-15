package com.example.todogreensoft

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.todogreensoft.adapter.ToDoAdapter
import com.example.todogreensoft.databinding.ActivityMainBinding
import com.example.todogreensoft.interfaces.IClickOnItem
import com.example.todogreensoft.roomDB.RoomInstance
import com.example.todogreensoft.roomDB.ToDo
import com.example.todogreensoft.viewModel.toDoListViewModel.ToDoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), IClickOnItem {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[ToDoViewModel::class.java]




        binding.add.setOnClickListener {
            val intent = Intent(this, AddNewToDo::class.java)
            intent.putExtra("pastActivity", "New")
            startActivity(intent)
        }


    }


    override fun onResume() {
        loadDataAndDisplayToRecyclerView()
        super.onResume()
    }


    private fun loadDataAndDisplayToRecyclerView() {
        CoroutineScope(Dispatchers.IO)
            .launch {
                val todoList: ArrayList<ToDo> = ArrayList()
                todoList.clear()
                todoList.addAll(
                    RoomInstance.getRoomInstance(this@MainActivity).toDoDAO().getAllToDoList()
                )

                withContext(Dispatchers.Main)
                {
                    binding.recyclerView.adapter = ToDoAdapter(todoList, this@MainActivity)


                }

            }
    }

    override fun onItemClickOfToDoItem(todo: ToDo) {


        val item = ToDo(todo.number, todo.title, todo.description)


        val intent = Intent(this, AddNewToDo::class.java)
        intent.putExtra("todo", item)
        intent.putExtra("pastActivity", "Edit")
        startActivity(intent)


    }
}