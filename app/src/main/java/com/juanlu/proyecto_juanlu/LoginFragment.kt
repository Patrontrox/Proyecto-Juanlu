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
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.juanlu.proyecto_juanlu.data.Usuario.UserPreferences
import com.juanlu.proyecto_juanlu.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Context.dataStore by preferencesDataStore(name = "settings")

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
            val usuario = binding.etUser.text.toString()
            val contrasena = binding.etContrasena.text.toString()
            var action = R.id.action_loginFragment_to_usuarioIntroFragment
            lifecycleScope.launch(Dispatchers.IO) {
                tomarDatos().collect {

                    withContext(Dispatchers.Main) {
                        action = if (it.checked) {
                            R.id.action_loginFragment_to_usuarioIntroFragment
                        } else {
                            R.id.action_loginFragment_to_menuFragment
                        }
                    }
                }
            }
            if (usuario.isEmpty() || contrasena.isEmpty()) {
                binding.etUser.error = "Introduce un usuario"
                binding.etContrasena.error = "Introduce una contraseña"
                return@setOnClickListener
            } else {
                findNavController().navigate(action)
                lifecycleScope.launch(Dispatchers.IO) {
                    saveUser(usuario, contrasena)
                }

            }

            val saveUsername =
                requireContext().getSharedPreferences("savedUsername", Context.MODE_PRIVATE)
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

        return binding.root
    }

    private fun tomarDatos() = binding.root.context.dataStore.data.map { preferences ->
        UserPreferences(
            usuario = preferences[stringPreferencesKey("usuario")].orEmpty(),
            contrasena = preferences[stringPreferencesKey("contrasena")].orEmpty(),
            checked = preferences[booleanPreferencesKey("checked")] ?: false

        )
    }

    private suspend fun saveUser(usuario: String, contrasena: String) {
        context?.dataStore?.edit { preferences ->
            preferences[stringPreferencesKey("usuario")] = usuario
            preferences[stringPreferencesKey("contrasena")] = contrasena
        }
    }

}