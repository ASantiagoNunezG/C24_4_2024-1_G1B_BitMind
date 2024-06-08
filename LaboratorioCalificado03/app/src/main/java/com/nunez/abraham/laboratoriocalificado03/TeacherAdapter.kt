package com.nunez.abraham.laboratoriocalificado03

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nunez.abraham.laboratoriocalificado03.databinding.ItemTeacherBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class TeacherAdapter(
    private var teachers: List<Teacher>,
    private val onItemClick: (Teacher) -> Unit,
    private val onItemLongClick: (Teacher) -> Unit
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher) {
            binding.teacherName.text = teacher.name
            binding.teacherLastName.text = teacher.last_name
            binding.teacherEmail.text = teacher.email
            binding.teacherPhoneNumber.text = teacher.phone_number

            Picasso.get()
                .load(teacher.image_url)
                .into(binding.teacherImage, object : Callback {
                    override fun onSuccess() {
                        // La imagen se cargó correctamente
                    }

                    override fun onError(e: Exception?) {
                        // Error al cargar la imagen
                        Log.e("Picasso", "Error loading image: ${e?.message}")
                    }
                })

            binding.root.setOnClickListener { onItemClick(teacher) }
            binding.root.setOnLongClickListener {
                onItemLongClick(teacher)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teachers[position])
    }

    override fun getItemCount() = teachers.size

    fun updateTeachers(newTeachers: List<Teacher>) {
        teachers = newTeachers
        notifyDataSetChanged()
    }
}