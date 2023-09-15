package com.example.superheroapp.response

import com.google.gson.annotations.SerializedName

/**
 *Clase que representa el Objeto para los detalles del super héroe
 */
data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperheroImageDetailResponse,
    @SerializedName("biography") val biography: Biography
)

/**
 *Clase que representa el Objeto para las estadísticas de poder de un super héroe
 */
data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

/**
 *Clase que representa el Objeto para obtener la URL de la imagen
 */
data class SuperheroImageDetailResponse(@SerializedName("url") val url: String)

/**
 *Clase que representa el Objeto para la biografía del súper héroe
 */
data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)
