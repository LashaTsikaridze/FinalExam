package com.example.finalexam.Main

import AnimalDatabaseHelper
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentHomeBinding
import com.example.finalexam.datebasa.Animal

class FragmentHome : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbHelper: AnimalDatabaseHelper
    private lateinit var adapter: AnimalAdapter
    private var animalList = mutableListOf<Animal>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        dbHelper = AnimalDatabaseHelper(requireContext())

        setupRecyclerView()
        setupSwipeToDelete()
    }

    private fun setupRecyclerView() {
        animalList = dbHelper.getAllAnimals().toMutableList()
        adapter = AnimalAdapter(animalList)
        binding.rvAnimals.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAnimals.adapter = adapter
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val animal = animalList[position]
                val deleted = dbHelper.deleteAnimal(animal.id)
                if (deleted) {
                    animalList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    // აქ აღარ გვაქვს Toast
                } else {
                    // წაშლა ვერ მოხდა, სიახლე დაუბრუნდეს
                    adapter.notifyItemChanged(position)
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.rvAnimals)
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {
        animalList.clear()
        animalList.addAll(dbHelper.getAllAnimals())
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

