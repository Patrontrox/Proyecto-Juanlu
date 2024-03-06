package com.juanlu.proyecto_juanlu.API

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanlu.proyecto_juanlu.databinding.FragmentBuscadorBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BuscadorFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentBuscadorBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RomsAdapter

    val romImages = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBuscadorBinding.inflate(inflater, container, false)
        binding.svBuscador.setOnQueryTextListener(this)
        inciarRecycler()
        return binding.root
    }

    private fun inciarRecycler() {
        adapter = RomsAdapter(romImages)
        binding.recyclerRoms.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerRoms.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://tu-servidor.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun buscarPorNombre(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada =
                getRetrofit().create(ApiService::class.java).getRomImage("$query/images")
            val roms = llamada.body()
            withContext(Dispatchers.Main) {
                if (llamada.isSuccessful) {
                    val listaRoms = roms?.foto ?: emptyList()
                    romImages.clear()
                    romImages.addAll(listaRoms)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Error en la busqueda", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            buscarPorNombre(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}