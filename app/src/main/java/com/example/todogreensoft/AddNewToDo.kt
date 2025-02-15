package com.example.todogreensoft

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.todogreensoft.databinding.ActivityAddNewToDoBinding
import com.example.todogreensoft.roomDB.ToDo
import com.example.todogreensoft.viewModel.AddNewPeopleViewModel

class AddNewToDo : AppCompatActivity() {


    private lateinit var todo: ToDo
    private lateinit var viewModel: AddNewPeopleViewModel


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
        viewModel = ViewModelProvider(this)[AddNewPeopleViewModel::class.java]



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

                    var newTODo= todo?.let { it1 ->
                        ToDo(
                            it1.number,  binding.title.text.toString(),
                            binding.description.text.toString())
                    }


                    if (newTODo != null) {
                        viewModel.updateExistingToDo(newTODo,this)
                    }
                    finish()



                }

                binding.deletebtn.setOnClickListener {

                    if (todo != null) {
                        viewModel.deleteTODo(todo, this)
                    }


                    finish()

                }

                binding.buttonisComplete.setOnClickListener {

                    if (todo != null) {
                        val newIsComplete = !todo.idComplete
                      viewModel.updateTick(todo,newIsComplete, this)

                    }

                    finish()

                }


            }

            "New" -> {
                binding.materialToolbar3.title="Create a new To Do"


                binding.save.setOnClickListener {
                    todo =
                        ToDo(0, binding.title.text.toString(), binding.description.text.toString())
                    viewModel.insertNewToDo(todo,this)
                    finish()

                }
            }
        }


    }


}