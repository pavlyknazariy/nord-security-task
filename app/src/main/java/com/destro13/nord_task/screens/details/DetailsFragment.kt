package com.destro13.nord_task.screens.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.destro13.nord_task.R
import com.destro13.nord_task.databinding.FragmentDetailsBinding
import com.destro13.nord_task.util.formatDate

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)
        initViews(binding)
    }

    private fun initViews(binding: FragmentDetailsBinding) {
        binding.run {
            val task = args.task
            titleTextView.text = task.title
            val timestamp = task.updatedAt.formatDate("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            lastUpdatedTextView.text = timestamp.formatDate("yyyy.MM.dd 'at' HH:mm:ss")
            stateTextView.text = if(task.completed) getString(R.string.completed) else getString(R.string.uncompleted)
        }
    }
}