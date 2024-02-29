package com.juanlu.proyecto_juanlu.data.Usuario

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanlu.proyecto_juanlu.databinding.FragmentDetailItemBinding

class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = FragmentDetailItemBinding.bind(view)

    fun render(usuarioModel: Usuario) {
        binding.tvUsuarioTop.text = usuarioModel.nombre
        binding.tvUsuarioBottom.text = usuarioModel.nombre
        Glide.with(binding.ivPhoto.context).load(usuarioModel.foto).into(binding.ivPhoto)
        binding.chkLike.setOnCheckedChangeListener { _, isChecked ->
            usuarioModel.isFavorite = isChecked
        }

        binding.ivPhoto.setOnClickListener {

            val enlance = usuarioModel.url
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(enlance))
            binding.ivPhoto.context.startActivity(intent)
        }
    }
}