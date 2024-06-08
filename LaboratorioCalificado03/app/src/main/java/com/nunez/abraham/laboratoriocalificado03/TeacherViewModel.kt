package com.nunez.abraham.laboratoriocalificado03

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call

class TeacherViewModel : ViewModel() {
    private val _teachers = MutableLiveData<List<Teacher>>()
    val teachers: LiveData<List<Teacher>> get() = _teachers

    init {
        viewModelScope.launch {
            _teachers.value = TeacherRepository.getTeachers()
        }
    }
}