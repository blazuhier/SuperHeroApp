package com.example.superheroapp.detail

import android.view.View
import com.example.superheroapp.response.PowerStatsResponse
import com.example.superheroapp.response.SuperHeroDetailResponse
import retrofit2.Retrofit

/**
 *Interface que implementa en SuperHeroDescriptionActivity
 */
interface SuperHeroDescriptionActivityView {

    /**
     *Método que inicializa los atributos de la Actividad
     */
    fun init()

    /**
     *Método que muestra la descripción del super héroe
     * @param idSuperHero recibe el id del super héroe en los extras de la actividad
     */
    fun superheroDescription(idSuperHero:String)

    /**
     *Método que postea los datos del súper héroe
     */
    fun setComponents(superhero: SuperHeroDetailResponse)

    /**
     *Método que postea los datos de los power stats del super héroe seleccionado
     */
    fun setPowerStats(powerstats: PowerStatsResponse)

    /**
     *Método que establece las dimensiones del layout a mostrar
     */
    fun setDimen(view: View, stat: String)

    /**
     *Método que convierte a DP las dimensiones del layout
     */
    fun setDp(px: Float): Int

    /**
     *Método que construye el cuerpo para la petición con Retrofit
     */
    fun getRequest(): Retrofit
}