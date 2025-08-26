import personagem.Personagem
import racas.*
import classes.*
import utils.*
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val atributosNomes = listOf(
        "Força", "Destreza", "Constituição", "Inteligência", "Sabedoria", "Carisma"
    )


    println("Escolha o estilo de jogo:")
    println("1 - Clássico")
    println("2 - Aventureiro")
    println("3 - Heroico")
    print("Digite o número: ")
    val estilo = when (scanner.nextInt()) {
        1 -> EstiloDistribuicao.CLASSICO
        2 -> EstiloDistribuicao.AVENTUREIRO
        3 -> EstiloDistribuicao.HEROICO
        else -> EstiloDistribuicao.CLASSICO
    }

    val gerador = GeradorAtributos(estilo)
    val rolagens = gerador.gerar().toMutableList()
    val atributosFinal = MutableList(6) { 0 }


    when (estilo) {
        EstiloDistribuicao.CLASSICO -> {
            println("\nRolagens automáticas (em ordem):")
            for (i in atributosNomes.indices) {
                atributosFinal[i] = rolagens[i]
                println("${atributosNomes[i]}: ${rolagens[i]}")
            }
        }
        EstiloDistribuicao.AVENTUREIRO, EstiloDistribuicao.HEROICO -> {
            println("\nValores rolados: $rolagens")
            println("Agora distribua esses valores entre os atributos:")

            for (i in atributosNomes.indices) {
                println("\nEscolha um valor para ${atributosNomes[i]}:")
                println("Valores disponíveis: $rolagens")
                val escolha = scanner.nextInt()
                if (escolha in rolagens) {
                    atributosFinal[i] = escolha
                    rolagens.remove(escolha)
                } else {
                    println("Valor inválido, atribuindo automaticamente ${rolagens.first()}")
                    atributosFinal[i] = rolagens.removeAt(0)
                }
            }
        }
    }


    println("\nDigite o nome do personagem:")
    scanner.nextLine() // consumir quebra de linha
    val nome = scanner.nextLine()


    println("\nEscolha a raça do personagem:")
    println("1 - Humano")
    println("2 - Elfo")
    println("3 - Meio-Demônio")
    val raca = when (scanner.nextInt()) {
        1 -> Humano()
        2 -> Elfo()
        3 -> MeioDemonio()
        else -> Humano()
    }


    println("\nEscolha a classe do personagem:")
    println("1 - Bardo")
    println("2 - Druida")
    println("3 - Mago")
    val classe = when (scanner.nextInt()) {
        1 -> Bardo()
        2 -> Druida()
        3 -> Mago()
        else -> Bardo()
    }


    val personagem = Personagem(nome, raca, classe)

    println("\n===== ATRIBUTOS DO PERSONAGEM =====")
    for (i in atributosNomes.indices) {
        println("${atributosNomes[i]}: ${atributosFinal[i]}")
    }
    println("===================================")

    println("\n===== FICHA COMPLETA =====")
    personagem.exibirFicha()
}
