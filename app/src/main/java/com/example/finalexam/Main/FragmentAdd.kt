package com.example.finalexam.Main

import AnimalDatabaseHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.finalexam.BackupWorker
import com.example.finalexam.R
import com.example.finalexam.datebasa.Animal


class FragmentAdd : Fragment(R.layout.fragment_add) {

    private lateinit var dbHelper: AnimalDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = AnimalDatabaseHelper(requireContext())

        val etName = view.findViewById<EditText>(R.id.etName)
        val etWeight = view.findViewById<EditText>(R.id.etWeight)
        val etLifespan = view.findViewById<EditText>(R.id.etLifespan)
        val etDescription = view.findViewById<EditText>(R.id.etDescription)
        val etHabitat = view.findViewById<EditText>(R.id.etHabitat)
        val etPhotoUri = view.findViewById<EditText>(R.id.etPhotoUri)
        val btnAdd = view.findViewById<Button>(R.id.btnAddAnimal)

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val weight = etWeight.text.toString().toDoubleOrNull() ?: 0.0
            val lifespan = etLifespan.text.toString().toIntOrNull() ?: 0
            val description = etDescription.text.toString()
            val habitat = etHabitat.text.toString()
            val photoUri = etPhotoUri.text.toString()

            if (name.isBlank() || description.isBlank() || habitat.isBlank()) {
                Toast.makeText(requireContext(), "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val animal = Animal(
                name = name,
                averageWeight = weight,
                lifespan = lifespan,
                description = description,
                habitat = habitat,
                photoUri = photoUri
            )

            dbHelper.insertAnimal(animal)
            Toast.makeText(requireContext(), "ცხოველი დამატებულია", Toast.LENGTH_SHORT).show()

            // 🔁 სარეზერვო ასლის შექმნა Firebase-ში (WorkManager One-Time)
            val backupRequest = OneTimeWorkRequestBuilder<BackupWorker>().build()
            WorkManager.getInstance(requireContext()).enqueue(backupRequest)

            // ველების გასუფთავება
            etName.text.clear()
            etWeight.text.clear()
            etLifespan.text.clear()
            etDescription.text.clear()
            etHabitat.text.clear()
            etPhotoUri.text.clear()
        }
    }
}
