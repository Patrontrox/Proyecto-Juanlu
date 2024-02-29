package com.juanlu.proyecto_juanlu.data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanlu.proyecto_juanlu.R
import com.juanlu.proyecto_juanlu.data.Usuario.UsuarioAdapter
import com.juanlu.proyecto_juanlu.databinding.FragmentItemListBinding

class ItemListFragment : Fragment() {

    private lateinit var usuarioAdapter: UsuarioAdapter

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        binding.recyclerFavoritos.layoutManager = LinearLayoutManager(context)

        usuarioAdapter = UsuarioAdapter(emptyList()) // Inicializa el adaptador con una lista vacía
        binding.recyclerFavoritos.adapter = usuarioAdapter

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // Aquí debes obtener la lista de usuarios favoritos y actualizar el adaptador
        val favoriteUsers = usuarioAdapter.getFavoriteUsers()
        usuarioAdapter.updateData(favoriteUsers)
    }
}