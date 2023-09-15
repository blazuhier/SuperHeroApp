package com.example.superheroapp.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapp.R
import com.example.superheroapp.response.Superhero

/**
 *Adaptador que conecta los datos con el view holder de cada super héroe
 */
class SuperHeroAdapter(
    /**
     *Variable para la lista de super héroes
     */
    private var superheroes: List<Superhero> = emptyList(),
    private val onItemSelected: (String) -> Unit) : RecyclerView.Adapter<SuperheroViewHolder>() {

    /**
     *Método para la actualización de la lista de super héroes y del adaptador
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(superheroList: List<Superhero>) {
        this.superheroes = superheroList
        notifyDataSetChanged()
    }

    /**
     *Método sobreescrito que devuelve una vista de cada elemento a mostrar
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    /**
     *Método sobreescrito que obtiene el tamaño del conjunto de datos
     */
    override fun getItemCount() = superheroes.size

    /**
     *Presenta los datos en una posición determinada
     */
    override fun onBindViewHolder(viewholder: SuperheroViewHolder, position: Int) {
        viewholder.bind(superheroes[position], onItemSelected)
    }
}