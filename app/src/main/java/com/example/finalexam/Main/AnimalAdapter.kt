package com.example.finalexam.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalexam.R
import com.example.finalexam.datebasa.Animal

class AnimalAdapter(private val animals: MutableList<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvWeight: TextView = itemView.findViewById(R.id.tvWeight)
        val tvLifespan: TextView = itemView.findViewById(R.id.tvLifespan)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvHabitat: TextView = itemView.findViewById(R.id.tvHabitat)
        val ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        holder.tvName.text = animal.name
        holder.tvWeight.text = "წონა: ${animal.averageWeight} კგ"
        holder.tvLifespan.text = "ცოცხლობს: ${animal.lifespan} წელი"
        holder.tvDescription.text = animal.description
        holder.tvHabitat.text = "ბინადრობს: ${animal.habitat}"

        // თუ photoUri არის ფოტოს ბმული ან ლოკალური Uri
        Glide.with(holder.itemView.context)
            .load(animal.photoUri)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.ivPhoto)
    }

    override fun getItemCount(): Int = animals.size

    fun deleteItem(position: Int) {
        animals.removeAt(position)
        notifyItemRemoved(position)
    }
}
