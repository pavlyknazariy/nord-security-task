package com.destro13.nord_task.screens.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.destro13.nord_task.databinding.ViewHolderTaskBinding
import com.destro13.nord_task.model.Task

class ToDoListAdapter(private val showMoreOnclickListener: (Int) -> Unit) :
    RecyclerView.Adapter<ToDoListAdapter.ToDoTaskViewHolder>() {
    private var tasks: List<Task> = emptyList()

    fun setData(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoTaskViewHolder =
        ToDoTaskViewHolder.create(parent, showMoreOnclickListener)

    override fun onBindViewHolder(holder: ToDoTaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    class ToDoTaskViewHolder private constructor(
        private val binding: ViewHolderTaskBinding,
        private val showMoreOnclickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.titleTextView.text = task.title
            binding.showMoreTextView.setOnClickListener {
                showMoreOnclickListener.invoke(task.taskId)
            }
        }

        companion object {
            fun create(parent: ViewGroup, showMoreOnclickListener: (Int) -> Unit) =
                ToDoTaskViewHolder(
                    ViewHolderTaskBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    showMoreOnclickListener
                )
        }
    }
}