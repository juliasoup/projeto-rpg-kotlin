package com.example.ktrpg

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CharacterViewModel : ViewModel() {

    var estilo by mutableStateOf(EstiloDistribuicao.CLASSICO) // <-- agora sem private set

    var selectedRaceIndex by mutableStateOf(0)
        private set

    var selectedClassIndex by mutableStateOf(0)
        private set

    var generatedValues by mutableStateOf(listOf<Int>())
        private set

    var assignedAttributes by mutableStateOf(List<Int?>(6) { null })
        private set

    var availableValues by mutableStateOf(listOf<Int>())
        private set

    fun setRaceIndex(i: Int) { selectedRaceIndex = i }

    fun setClassIndex(i: Int) { selectedClassIndex = i }

    fun gerarRolagens() {
        val gerador = GeradorAtributos(estilo)
        val rolagens = gerador.gerar()
        generatedValues = rolagens

        assignedAttributes = List(6) { null }

        availableValues = rolagens.toMutableList()
    }

    fun assignValueToAttribute(attrIndex: Int, value: Int?) {
        val newAssigned = assignedAttributes.toMutableList()
        val previous = newAssigned[attrIndex]
        val newAvailable = availableValues.toMutableList()

        if (previous != null) {
            newAvailable.add(previous)
        }
        if (value != null) {
            val idx = newAvailable.indexOf(value)
            if (idx >= 0) newAvailable.removeAt(idx)
        }
        newAssigned[attrIndex] = value
        assignedAttributes = newAssigned
        availableValues = newAvailable
    }

    fun buildPersonagem(nome: String): Personagem {
        val race = when (selectedRaceIndex) {
            0 -> Humano()
            1 -> Elfo()
            else -> MeioDemonio()
        }
        val klass = when (selectedClassIndex) {
            0 -> Bardo()
            1 -> Druida()
            else -> Mago()
        }

        val atributosFinal: List<Int> = when (estilo) {
            EstiloDistribuicao.CLASSICO -> generatedValues.take(6)
            else -> assignedAttributes.map { it ?: 0 }
        }

        return Personagem(nome, race, klass, atributosFinal)
    }
}
