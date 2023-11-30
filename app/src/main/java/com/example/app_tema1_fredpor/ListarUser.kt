package com.example.app_tema1_fredpor

object ListarUser {
    private var usuarios: ArrayList<Usuario> = ArrayList<Usuario>()
    fun annadirUsuario(nuevoUsuario: Usuario){ this.usuarios.add(nuevoUsuario) }

    fun obtenerUsuario(): ArrayList<Usuario> { return this.usuarios }
}