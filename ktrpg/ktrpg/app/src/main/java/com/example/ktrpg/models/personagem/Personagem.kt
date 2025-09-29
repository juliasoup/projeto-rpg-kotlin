package com.example.ktrpg

class Personagem(
    val nome: String,
    val raca: Raca,
    val classe: Classes,
    val atributos: List<Int> = listOf(10,10,10,10,10,10) // default
) {
    fun exibirFicha(): String {
        val attrsLabels = listOf("Força","Destreza","Constituição","Inteligência","Sabedoria","Carisma")
        return buildString {
            appendLine("=== Ficha do Personagem ===")
            appendLine("Nome: $nome")
            appendLine("Raça: ${raca::class.simpleName}")
            appendLine("Movimento: ${raca.movimento}")
            appendLine("Infravisão: ${raca.infravisao}")
            appendLine("Alinhamento: ${raca.alinhamento}")
            appendLine("Habilidades da Raça: ${raca.habilidades().joinToString(", ")}")
            appendLine()
            appendLine("--- Atributos ---")
            attrsLabels.forEachIndexed { idx, label ->
                appendLine("$label: ${atributos.getOrNull(idx) ?: "-"}")
            }
            appendLine()
            appendLine("--- Informações da Classe ---")
            appendLine(classe.exibirInfos())
            appendLine("===========================")
        }
    }
}