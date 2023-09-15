package com.example.superheroapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.example.superheroapp.remote.APIService
import com.example.superheroapp.response.PowerStatsResponse
import com.example.superheroapp.response.SuperHeroDetailResponse
import com.example.superheroapp.databinding.ActivitySuperheroDescBinding
import com.example.superheroapp.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

/**
 *Clase que muestra la descripción del super héroe seleccionado
 */
class SuperHeroDescriptionActivity : AppCompatActivity(), SuperHeroDescriptionActivityView {

    /**
     *Variables de instancia
     */
    private lateinit var binding: ActivitySuperheroDescBinding

    /**
     *Método que inicializa la Actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroDescBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    /**
     *Método que inicializa los atributos de la Actividad
     */
    override fun init() {
        val id: String = intent.getStringExtra(Constants.EXTRA_ID).orEmpty()
        superheroDescription(id)
    }

    /**
     *Método que muestra la descripción del super héroe
     * @param idSuperHero recibe el id del super héroe en los extras de la actividad
     */
    override fun superheroDescription(idSuperHero: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail = getRequest().create(APIService::class.java).getSuperheroDetail(idSuperHero)
            if (superheroDetail.body() != null){
                runOnUiThread { setComponents(superheroDetail.body()!!) }
            }
        }
    }

    /**
     *Método que postea los datos del súper héroe
     */
    override fun setComponents(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvNameSuperHero.text = superhero.name
        setPowerStats(superhero.powerstats)
        binding.tvFullNameSuperHero.text = superhero.biography.fullName
        binding.tvEditor.text = superhero.biography.publisher
    }

    /**
     *Método que postea los datos de los power stats del super héroe seleccionado
     */
    override fun setPowerStats(powerstats: PowerStatsResponse) {
        setDimen(binding.viewIntelligence, powerstats.intelligence)
        setDimen(binding.viewStrength, powerstats.strength)
        setDimen(binding.viewSpeed, powerstats.speed)
        setDimen(binding.viewDurability, powerstats.durability)
        setDimen(binding.viewPower, powerstats.power)
        setDimen(binding.viewCombat, powerstats.combat)
    }

    /**
     *Método que establece las dimensiones del layout a mostrar
     */
    override fun setDimen(view: View, stat: String) {
        val params = view.layoutParams
        if(stat != "null") params.height = setDp(stat.toFloat()) else params.height = setDp(0.00.toFloat())
        view.layoutParams = params
    }

    /**
     *Método que convierte a DP las dimensiones del layout
     */
    override fun setDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()

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