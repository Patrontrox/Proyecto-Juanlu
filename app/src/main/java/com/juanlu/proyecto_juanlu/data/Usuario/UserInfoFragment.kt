package com.juanlu.proyecto_juanlu.data.Usuario

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.juanlu.proyecto_juanlu.CreditsFragment
import com.juanlu.proyecto_juanlu.R
import com.juanlu.proyecto_juanlu.data.FavItemFragment
import com.juanlu.proyecto_juanlu.dataStore
import com.juanlu.proyecto_juanlu.databinding.FragmentFavItemBinding
import com.juanlu.proyecto_juanlu.databinding.FragmentUserInfoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        binding.vpNotice.adapter = NoticeAdapter(this)

        TabLayoutMediator(binding.tabMover, binding.vpNotice) { tab, position ->
            tab.text = when (position) {
                0 -> "Perfil"
                else -> "Creditos"
            }
        }.attach()

        lifecycleScope.launch(Dispatchers.IO) {
            getData().collect {
                withContext(Dispatchers.Main) {
                    FragmentFavItemBinding.bind(binding.root).tvUsuario.text = it.usuario
                    FragmentFavItemBinding.bind(binding.root).tvUsuarioDescripcion.text = it.contrasena
                }
            }
        }
        return binding.root
    }

    private fun getData() = binding.root.context.dataStore.data.map { preferences ->
        UserPreferences(
            usuario = preferences[stringPreferencesKey("usuario")] ?: "",
            contrasena = preferences[stringPreferencesKey("contrasena")] ?: "",
            checked = preferences[booleanPreferencesKey("checked")] ?: false
        )
    }

    class NoticeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment {
            val fragment = if (position == 0) FavItemFragment()
            else CreditsFragment()

            return fragment
        }
    }
}