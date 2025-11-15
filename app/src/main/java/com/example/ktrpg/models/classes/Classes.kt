package com.example.ktrpg

open class Classes(
    val nome: String,
    val habilidades: String = "",
    val especializacoes: String = ""
) {
    open fun exibirInfos(): String {
        return buildString {
            appendLine("Classe: $nome")
            if (habilidades.isNotBlank()) appendLine("Habilidades:\n$habilidades")
            if (especializacoes.isNotBlank()) appendLine("Especializações:\n$especializacoes")
        }
    }
}