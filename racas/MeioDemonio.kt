package racas

import racas.Raca

class MeioDemonio : Raca(
    movimento = 14,
    infravisao = 15,
    alinhamento = "Neutro"
){
    override fun habilidades(): List<String> {
        return listOf("Força Sobrenatural", "Magia poderosa")
    }
}