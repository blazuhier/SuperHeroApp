package com.example.superheroapp.response

import com.google.gson.annotations.SerializedName

/**
 *Clase para representar la respuesta de la petición
 */
class SuperHeroResponse (
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<Superhero>
    )

/**
 *Clase para mapear los datos recibidos de la petición
 */
    data class Superhero(
        @SerializedName("id") val superheroId: String,
        @SerializedName("name") val name: String,
        @SerializedName("image") val superheroImage: SuperheroImageResponse
    )

/**
 *Clase para mapear la URL que se recibe en el response
 */
    data class SuperheroImageResponse(@SerializedName("url") val url: String)
