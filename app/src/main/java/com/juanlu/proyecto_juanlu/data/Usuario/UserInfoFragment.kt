package com.juanlu.proyecto_juanlu.data.Usuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.juanlu.proyecto_juanlu.CreditsFragment
import com.juanlu.proyecto_juanlu.R
import com.juanlu.proyecto_juanlu.data.FavItemFragment
import com.juanlu.proyecto_juanlu.databinding.FragmentUserInfoBinding

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

        return binding.root
    }

    class NoticeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment {
            // Return a NEW fragment instance in createFragment(int)
            val fragment = if (position == 0) FavItemFragment()
            else CreditsFragment()

            return fragment
        }
    }
}