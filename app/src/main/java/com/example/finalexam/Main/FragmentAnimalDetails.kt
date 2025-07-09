package com.example.finalexam.Main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalexam.R

class FragmentAnimalDetails : Fragment(R.layout.fragment_animal_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameText = view.findViewById<TextView>(R.id.textViewName)
        val speciesText = view.findViewById<TextView>(R.id.textViewSpecies)
        val ageText = view.findViewById<TextView>(R.id.textViewAge)

        val args = arguments
        val id = args?.getInt("id") ?: -1
        val name = args?.getString("name") ?: "Unknown"
        val species = args?.getString("species") ?: "Unknown"
        val age = args?.getInt("age") ?: 0

        nameText.text = "სახელი: $name"
        speciesText.text = "სახეობა: $species"
        ageText.text = "ასაკი: $age წლის"
    }
}
