package models.personagem

import models.classes.Classes
import models.racas.Raca

class Personagem(val nome: String, val raca: Raca, val classes: Classes) {
    fun exibirFicha() {
        println("=== Ficha do Personagem ===")
        println("Nome: $nome")
        println("Raça: ${raca::class.simpleName}")
        println("Movimento: ${raca.movimento}")
        println("Infravisão: ${raca.infravisao}")
        println("Alinhamento: ${raca.alinhamento}")
        println("Habilidades da Raça: ${raca.habilidades().joinToString()}")
        classes.exibirInfos()
    }
}