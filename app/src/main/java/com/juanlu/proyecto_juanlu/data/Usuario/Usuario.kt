package com.juanlu.proyecto_juanlu.data.Usuario

data class Usuario (
    val nombre: String,
    val descripcion: String,
    val contrasena: String,
    val nombreROM: String,
    val foto: String,
    val fotoPerfil: String,
    val url: String,
    var isFavorite: Boolean = false
)