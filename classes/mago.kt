package org.example.classes


class Mago : Classes(
    nome = "Mago",
    habilidades = """
        - Armas: Apenas pequenas. Grandes e médias geram dificuldade para os magos.
        - Armaduras: Nenhuma. Escudos e armaduras impedem a conjuração de magias e protegem apenas metade da CA normal.
        - Itens mágicos: Podem usar de todos os tipos.
        - Magias arcanas:
          * O Mago é capaz de aprender magias e lançá-las por meio de seu grimório.
          * Magias Iniciais: no 1º nível, o grimório possui três magias de 1º círculo escolhidas e uma magia aleatória da lista.
          * Novas Magias: um Mago pode acrescentar livremente novas magias ao seu grimório copiando de outros livros ou pergaminhos.
          * Círculos Superiores: um Mago pode escrever no grimório magias de círculos acima de sua capacidade, mas não pode memorizá-las até atingir o nível necessário.
        - Ler Magias: Um Mago pode, uma vez ao dia por nível, decifrar e ler inscrições mágicas em qualquer lugar, mesmo sem compreender seu significado. Essa habilidade não revela propriedades de itens mágicos, nem é necessária para lançar magias escritas, mas permite identificar qual magia está registrada.
        - Detectar Magias: O Mago pode, 1 vez ao dia por nível, perceber a presença de magia em uma área de 9m + 3m por nível, desde que esteja concentrado e procurando ativamente. Ele vê apenas uma aura mágica tênue, sem identificar efeitos, nível de poder ou o tipo de magia.
          * Concentração necessária: 1d8 rodadas (reduz para 1d4 no 6º nível e 1 rodada no 10º nível).
    """.trimIndent(),
    especializacoes = " "
)
{
    override fun exibirInfos() {
        println("Nome: $nome")
        println("Habilidades:")
        habilidades.forEach { println("  - $it") }
        println("Especializações: $especializacoes")
    }
}
