package org.example.racas

open class Raca(val nome: String, val fisico: String = "", val habilidade: String = "") {

    open fun exibirInfos() {
        println("Nome: $nome, Físico: $fisico, Habilidade: $habilidade")
    }
}