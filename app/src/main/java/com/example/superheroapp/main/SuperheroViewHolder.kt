package com.example.superheroapp.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.databinding.ItemSuperheroBinding
import com.example.superheroapp.response.Superhero
import com.squareup.picasso.Picasso

/**
 *Clase que establece los datos del holder de cada elemento
 */
class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     *Variable que enlaza los componentes con su vista
     */
    private val binding = ItemSuperheroBinding.bind(view)

    /**
     *Método que inicializa los componentes de cada item
     */
    fun bind(superheroItemResponse: Superhero, onItemSelected: (String) -> Unit) {
        binding.tvNameSuperHero.text = superheroItemResponse.name
        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener { onItemSelected(superheroItemResponse.superheroId) }
    }
}