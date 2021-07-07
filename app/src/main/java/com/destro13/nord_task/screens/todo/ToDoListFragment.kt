package com.destro13.nord_task.screens.todo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.destro13.nord_task.R
import com.destro13.nord_task.databinding.FragmentToDoListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListFragment : Fragment(R.layout.fragment_to_do_list) {

    private val viewModel: ToDoViewModel by viewModels()

    private var toDoListAdapter: ToDoListAdapter? = null

    override fun onStart() {
        super.onStart()
        viewModel.getToDoList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentToDoListBinding.bind(view)

        initViews(binding)
        initObservers(binding)
    }

    private fun initViews(binding: FragmentToDoListBinding) {
        binding.run {
            toDoListRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                toDoListAdapter = ToDoListAdapter {
                    viewModel.showTaskDetails(it)
                }
                adapter = toDoListAdapter
            }
        }
    }

    private fun initObservers(binding: FragmentToDoListBinding) {
        viewModel.run {
            toDoListLiveData.observe(viewLifecycleOwner) {
                toDoListAdapter?.setData(it)
            }

            taskDetailsLiveData.observe(viewLifecycleOwner) {
                val action = ToDoListFragmentDirections.openDetails(it)
                Navigation.findNavController(binding.root).navigate(action)
            }

            errorLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(activity, it.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}