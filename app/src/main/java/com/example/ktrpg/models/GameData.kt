package com.example.ktrpg.models

import com.example.ktrpg.Bardo
import com.example.ktrpg.Classes
import com.example.ktrpg.Druida
import com.example.ktrpg.Elfo
import com.example.ktrpg.Humano
import com.example.ktrpg.Mago
import com.example.ktrpg.MeioDemonio
import com.example.ktrpg.Raca

object GameData {
    val classes = listOf(Bardo(), Druida(), Mago())
    val racas = listOf(Elfo(), Humano(), MeioDemonio())

    fun getClasse(name: String): Classes? = classes.find { it.nome == name }
    fun getRaca(name: String): Raca? = racas.find { it.javaClass.simpleName == name }
}
