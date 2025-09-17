package models.classes

class Druida: Classes(
    nome = "Druida",

    habilidades = """
         - Herbalismo (nível 1): identificar plantas, animais e reconhecer água pura;
        - Previdência (nível 3): acampamentos criados pelo Druida são sempre seguros;
        - Transformação (nível 6): pode assumir a forma de um animal pequeno não-mágico de até 6 DV (3 vezes ao dia);
        - Transformação Melhorada (nível 10): pode assumir a forma de um animal não-mágico de até 10 DV (3 vezes ao dia).
    """.trimIndent(),

    especializacoes = """
        - Conjura magias como um Clérigo do mesmo nível;
        - Não possui Cura Milagrosa nem Afastar Mortos-Vivos.
    """.trimIndent()
) {
    private val restricoes = listOf(
        "Deve possuir alinhamento Neutro",
        "Não pode usar armas ou armaduras metálicas (perde habilidades se usar)"
    )
    override fun exibirInfos() {
        super.exibirInfos()
        println("Restrições: ${restricoes.joinToString("; ")}")
        }
}