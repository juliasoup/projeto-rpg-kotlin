package com.example.ktrpg



enum class EstiloDistribuicao {
    CLASSICO, AVENTUREIRO, HEROICO
}

class GeradorAtributos(private val estilo: EstiloDistribuicao){

    fun gerar(): List<Int> {
        return when (estilo) {
            EstiloDistribuicao.CLASSICO ->
                List(6) { DiceRoller.roll3d6() }

            EstiloDistribuicao.AVENTUREIRO ->
                List(6) { DiceRoller.roll3d6() }

            EstiloDistribuicao.HEROICO ->
                List(6) { DiceRoller.roll4d6DropMen() }
        }
    }
}