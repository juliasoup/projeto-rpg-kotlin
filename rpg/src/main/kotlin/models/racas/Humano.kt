package models.racas

class Humano : Raca(
    movimento = 9,
    infravisao = 0,
    alinhamento = "Qualquer"
) {
    override fun habilidades(): List<String> {
        return listOf(
            "Versatilidade: ganha +1 ponto de atributo à escolha na criação",
            "Adaptação: pode escolher qualquer classe sem restrição"
        )
    }
}