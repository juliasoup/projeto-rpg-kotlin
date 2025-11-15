package com.example.ktrpg

class Elfo : Raca(
    movimento = 9,
    infravisao = 18,
    alinhamento = "Neutro"
){
    override fun habilidades(): List<String> {
        return listOf("Percepção Natural", "Graciosos", "Arma Racial", "Imunidades")
    }
}