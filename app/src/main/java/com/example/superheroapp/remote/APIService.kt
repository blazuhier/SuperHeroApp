package com.example.superheroapp.remote

import com.example.superheroapp.response.SuperHeroDetailResponse
import com.example.superheroapp.response.SuperHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *Interface para manejar las peticiones generales para la información del Súper Héroe
 */
interface APIService {

    /**
     *Método tipo GET que realiza una petición para buscar un super héroe por nombre
     *@param superheroName parámetro que recibe el nombre del súper héroe
     */
    @GET("api/231823636525694/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroResponse>

    /**
     *Petición tipo GET que solicita los datos del super héroe
     *@param superheroId parámetro que recibe el id del súper héroe
     */
    @GET("api/231823636525694/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId: String): Response<SuperHeroDetailResponse>
}