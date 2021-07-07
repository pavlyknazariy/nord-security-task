package com.destro13.nord_task.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.destro13.nord_task.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}