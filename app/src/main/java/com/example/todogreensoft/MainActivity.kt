package com.example.todogreensoft

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.todogreensoft.adapter.ToDoAdapter
import com.example.todogreensoft.databinding.ActivityMainBinding
import com.example.todogreensoft.interfaces.IClickOnItem
import com.example.todogreensoft.roomDB.ToDo
import com.example.todogreensoft.viewModel.ToDoViewModel

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
        viewModel.getAllTODOList(this)
        viewModel.todoList.observe(this){
            binding.recyclerView.adapter = ToDoAdapter(it, this@MainActivity)
            Log.d("ABCDEF",it.size.toString())

        }
        super.onResume()
    }

    override fun onItemClickOfToDoItem(todo: ToDo) {

        val item = ToDo(todo.number, todo.title, todo.description)

        val intent = Intent(this, AddNewToDo::class.java)
        intent.putExtra("todo", item)
        intent.putExtra("pastActivity", "Edit")
        startActivity(intent)


    }
}