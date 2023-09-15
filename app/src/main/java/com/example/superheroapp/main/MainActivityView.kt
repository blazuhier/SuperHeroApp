package com.example.superheroapp.main

import com.example.superheroapp.response.Superhero
import retrofit2.Retrofit

interface MainActivityView{

    /**
    *Método que inicializa los atributos de la Actividad
     */
    fun init()

    /**
    *Método que inicializa los eventos de atributos de la Actividad
     */
    fun initListeners()

    /**
    *Método que ejecuta el query para la petición de los datos
    *@name parámetro que proporciona el nombre del super héroe para la petición
    */
    fun getSuperHero(name : String)

    /**
     *Método que muestra los resultados de la petición en la UI
     *@name parámetro que proporciona el nombre del super héroe para la petición
     */
    fun showSuperHero(response: List<Superhero>, result: Boolean)

    /**
     *Método que abre la actividad de SuperHeroDescriptionAcitivy
     *@idSuperHero parámetro que se integra a los extras del intent con el ID del super héroe
     */
    fun openDetailActivity(idSuperHero: String)

    /**
     *Método que construye el cuerpo para la petición con Retrofit
     */
    fun getRequest():Retrofit
}