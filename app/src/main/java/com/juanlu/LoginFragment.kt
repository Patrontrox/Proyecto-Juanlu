package com.juanlu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.juanlu.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.buttonLogin.setOnClickListener {
            val username = binding.idUser.text.toString()
            val saveUsername = requireContext().getSharedPreferences("savedUsername", Context.MODE_PRIVATE)
            saveUsername.edit().putString("username", username).apply()
            findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}