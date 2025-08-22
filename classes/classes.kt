package org.example.classes

open class Classes(val nome: String, val habilidades: String = "", val especializacoes: String = "") {

    open fun exibirInfos() {
        println("Nome: $nome")
        println("Habilidades: $habilidades")
        println("Especializacoes: $especializacoes")
    }
}