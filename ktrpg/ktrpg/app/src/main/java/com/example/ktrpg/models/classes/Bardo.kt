package com.example.ktrpg

class Bardo : Classes(
    nome = "Bardo",
    habilidades = """
        - Armas: podem usar armas leves (espadas curtas, adagas, arcos curtos, etc.).
        - Armaduras: podem usar armaduras leves sem prejudicar suas habilidades.
        - Magias Arcanas: conjuram magias arcanas de forma semelhante ao Mago, mas com progressão própria.
        - Música Inspiradora: podem inspirar aliados, concedendo bônus em testes, jogadas de ataque ou resistência.
        - Conhecimento: possuem vasto saber geral, podendo identificar criaturas, objetos ou lendas.
        - Ladrinagem: acesso a algumas perícias de ladino, como furtividade e enganação.
    """.trimIndent(),
    especializacoes = """
        - O Bardo combina magia, combate leve e habilidades sociais.
        - É versátil, podendo atuar como apoio ao grupo ou até mesmo como líder carismático.
    """.trimIndent()
) {
    private val restricoes = listOf(
        "Não pode usar armaduras médias ou pesadas",
        "Dependente de Carisma para suas habilidades principais"
    )

    override fun exibirInfos(): String {
        return super.exibirInfos() + "\nRestrições: ${restricoes.joinToString("; ")}"
    }
}