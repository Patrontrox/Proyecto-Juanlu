package com.juanlu.proyecto_juanlu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.juanlu.proyecto_juanlu.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        binding.btIniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_menuFragment)

            val usuario = binding.etUser.text.toString()
            val saveUsername = requireContext().getSharedPreferences("savedUsername", Context.MODE_PRIVATE)
            saveUsername.edit().putString("username", usuario).apply()
        }

        binding.ivImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "https://www.ieslamarisma.net/".toUri()
            startActivity(intent)
        }

        binding.swTheme.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        /*val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.credits -> {
                        findNavController().navigate(R.id.action_menuFragment_to_creditsFragment)
                        true
                    }
                    R.id.perfil -> {
                        findNavController().navigate(R.id.action_menuFragment_to_loginFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)*/

        return binding.root
    }

}