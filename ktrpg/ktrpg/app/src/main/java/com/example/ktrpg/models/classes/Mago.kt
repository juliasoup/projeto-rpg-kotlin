package com.example.ktrpg

class Mago : Classes(
    nome = "Mago",

    habilidades = """
        - Armas: apenas pequenas. Armas grandes ou médias geram ataques difíceis para Magos;
        - Armaduras: nenhuma. Usar escudos ou armaduras impede a conjuração de magias e protegem apenas metade da CA normal;
        - Itens Mágicos: podem usar todos os tipos;
        - Magias Arcanas (nível 1): pode lançar magias arcanas diariamente, memorizadas a partir de seu grimório;
        - Grimório: livro onde o Mago escreve sua coleção de magias. Sem ele, não pode memorizar novas magias;
        - Magias Iniciais (nível 1): o grimório contém 3 magias de 1º círculo à escolha + 1 magia aleatória da lista de 1º círculo;
        - Novas Magias: pode acrescentar magias ao grimório copiando de outros grimórios ou pergaminhos;
        - Círculos Superiores: pode escrever magias de círculos acima de sua capacidade, mas só memorizá-las quando atingir o nível necessário;
        - Ler Magias (nível 1): uma vez ao dia por nível, pode decifrar inscrições mágicas em qualquer lugar;
        - Detectar Magias (nível 1): uma vez ao dia por nível, percebe a presença de magia em uma área de até 9m + 3m/nível. 
          Requer concentração de 1d8 rodadas (1d4 no nível 6, 1 rodada no nível 10).
    """.trimIndent(),

    especializacoes = """
        - Durante a criação do personagem, ou até o fim do 1º nível, o Mago pode assumir uma especialização;
        - Um Especialista mantém habilidades da classe base, evolui pela coluna XP Especial e pode ter restrições adicionais.
    """.trimIndent()
) {
    private val restricoes = listOf(
        "Não pode usar armaduras (mesmo escudos atrapalham magias)",
        "Só pode usar armas pequenas",
        "Dependência do grimório para memorizar magias"
    )

    override fun exibirInfos(): String {
        return super.exibirInfos() + "\nRestrições: ${restricoes.joinToString("; ")}"
    }
}