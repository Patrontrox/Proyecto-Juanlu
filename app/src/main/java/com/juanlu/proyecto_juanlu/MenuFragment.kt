package com.juanlu.proyecto_juanlu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanlu.proyecto_juanlu.data.Usuario.UsuarioAdapter
import com.juanlu.proyecto_juanlu.data.Usuario.UsuarioProvider
import com.juanlu.proyecto_juanlu.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

/**val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.perfil -> {
                        findNavController().navigate(R.id.action_menuFragment_to_userInfoFragment)
                        true
                    }
                    R.id.favoritos -> {
                        findNavController().navigate(R.id.action_menuFragment_to_itemListFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)*/

        inciarRecycler()
        return binding.root
    }

    private fun inciarRecycler(){
        binding.recyclerPublicaciones.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerPublicaciones.adapter = UsuarioAdapter(UsuarioProvider.listaUsuarios)
    }

}