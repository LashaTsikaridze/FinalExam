package com.example.finalexam.Main

import AnimalDatabaseHelper
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.finalexam.BackupWorker
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentHomeBinding
import com.example.finalexam.datebasa.Animal
import java.util.concurrent.TimeUnit

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
        scheduleBackupWorker(requireContext())
    }

    private fun setupRecyclerView() {
        animalList = dbHelper.getAllAnimals().toMutableList()
        adapter = AnimalAdapter(animalList) { animal ->
            // Bundle-ით მონაცემების გადაცემა fragmentebs შორის
            val bundle = Bundle().apply {
                putInt("id", animal.id)
                putString("name", animal.name)
                putString("species", animal.habitat)

            }
            findNavController().navigate(R.id.fragmentAnimalDetails, bundle)
        }
        binding.rvAnimals.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAnimals.adapter = adapter
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
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
                } else {
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

    private fun scheduleBackupWorker(context: Context) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<BackupWorker>(6, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "animal_backup_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
