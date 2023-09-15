package com.example.superheroapp.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.remote.APIService
import com.example.superheroapp.detail.SuperHeroDescriptionActivity
import com.example.superheroapp.response.SuperHeroResponse
import com.example.superheroapp.response.Superhero
import com.example.superheroapp.databinding.ActivityMainBinding
import com.example.superheroapp.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Clase principal de la aplicación
 */
class MainActivity : AppCompatActivity(), MainActivityView {

    /**
     *Variables de instancia
     */
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter
    private lateinit var toast: Toast

    /**
     *Método que inicializa la Actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     *Método que inicializa los atributos de la Actividad
     */
    override fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRequest()
        initListeners()
    }

    /**
     *Método que inicializa los eventos de atributos de la Actividad
     */
    override fun initListeners() {
        binding.wgViewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSuperHero(query.orEmpty())
                return false
            }
            override fun onQueryTextChange(newText: String?) = false
        })

        binding.ivResult.isVisible = true
        adapter = SuperHeroAdapter() { superheroId -> openDetailActivity(superheroId) }
        binding.rvDatas.setHasFixedSize(true)
        binding.rvDatas.layoutManager = LinearLayoutManager(this)
        binding.rvDatas.adapter = adapter
    }

    /**
     *Método que ejecuta el query para la petición de los datos
     *@name parámetro que proporciona el nombre del super héroe para la petición
     */
    override fun getSuperHero(name: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val res: Response<SuperHeroResponse> =
                retrofit.create(APIService::class.java).getSuperheroes(name)
            if (res.isSuccessful) {
                val response: SuperHeroResponse = res.body()!!
                if (response.response != Constants.ERROR_RESPONSE) {
                    runOnUiThread { showSuperHero(response.superheroes, false) }
                } else {
                    runOnUiThread { showSuperHero(emptyList(), true) }
                }
            } else {
                runOnUiThread { showSuperHero(emptyList(), true) }
            }
        }
    }

    /**
     *Método que muestra los resultados de la petición en la UI
     *@name parámetro que proporciona el nombre del super héroe para la petición
     */
    override fun showSuperHero(response: List<Superhero>, result: Boolean) {
        var textToast = "No se encontró el súper héroe"
        adapter.updateList(response)
        binding.progressBar.isVisible = false
        binding.ivResult.isVisible = result
        if (!result) textToast = "Resultados de la búsqueda"
        toast = Toast.makeText(this, textToast, Toast.LENGTH_LONG)
        toast.show()
    }

    /**
     *Método que abre la actividad de SuperHeroDescriptionAcitivy
     *@idSuperHero parámetro que se integra a los extras del intent con el ID del super héroe
     */
    override fun openDetailActivity(idSuperHero: String) {
        val intent = Intent(this, SuperHeroDescriptionActivity::class.java)
        intent.putExtra(Constants.EXTRA_ID, idSuperHero)
        startActivity(intent)
    }

    /**
     *Método que construye el cuerpo para la petición con Retrofit
     */
    override fun getRequest(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}