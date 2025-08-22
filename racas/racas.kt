package org.example.racas

open class Raca(val nome: String, val fisico: String = "", val habilidade: String = "") {

    open fun exibirInfos() {
        println("Nome: $nome")
        println("Físico: $fisico")
        println("Habilidade: $habilidade")
    }
}
