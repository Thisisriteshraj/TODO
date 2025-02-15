package com.example.todogreensoft.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todogreensoft.databinding.ToDoItemBinding
import com.example.todogreensoft.interfaces.IClickOnItem
import com.example.todogreensoft.roomDB.ToDo

class ToDoAdapter(private var toDoList: ArrayList<ToDo>, private var onItemClick: IClickOnItem) :
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    class ViewHolder(var binding: ToDoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ViewHolder {

        return ViewHolder(
            ToDoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    }

    override fun onBindViewHolder(holder: ToDoAdapter.ViewHolder, position: Int) {
        holder.binding.title.text = toDoList[position].title
        holder.binding.cardView.setOnClickListener {
            onItemClick.onItemClickOfToDoItem(toDoList[position])
        }


        if (toDoList[position].idComplete) {

            holder.binding.isComplete.visibility = View.VISIBLE
        } else
            holder.binding.isComplete.visibility = View.GONE


    }

    override fun getItemCount(): Int {
        return toDoList.size

    }
}