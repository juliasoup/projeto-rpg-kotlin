package classes

open class Classes(
    val nome: String,
    val habilidades: String = "",
    val especializacoes: String = ""
) {
    open fun exibirInfos() {
        println("Classe: $nome")
        println("Habilidades: $habilidades")
        println("Especializações: $especializacoes")
    }
}