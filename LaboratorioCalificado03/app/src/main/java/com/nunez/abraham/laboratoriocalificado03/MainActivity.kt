package com.nunez.abraham.laboratoriocalificado03

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunez.abraham.laboratoriocalificado03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var teacherAdapter: TeacherAdapter
    private lateinit var teacherViewModel: TeacherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        teacherAdapter = TeacherAdapter(emptyList(), this::onItemClick, this::onItemLongClick)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = teacherAdapter

        teacherViewModel = ViewModelProvider(this).get(TeacherViewModel::class.java)

        teacherViewModel.teachers.observe(this) { teachers ->
            teacherAdapter.updateTeachers(teachers)
        }
    }

    private fun onItemClick(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${teacher.phone_number}")
        startActivity(intent)
    }

    private fun onItemLongClick(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:${teacher.email}")
        startActivity(intent)
    }
}

