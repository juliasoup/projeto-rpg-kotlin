package org.example.racas

class elfo : Raca("Elfo",
        "Esguios, franzinos e mais baixos comparados com um humano, possuem cabelos compridos com orelhas longas e pontudas.\n" +
                " São frios e prepotentes com dificuldades de criar laços de amizade\n",
        "Percepção natural: percepção especial no que diz respeito a portas e passagens não convencionais e até mesmo secretas.\n" +
                "Graciosidade: controlam com precisão seus movimentos no espaço ao redor do seu corpo, recebendo um bônus de +1 em qualquer teste de JPD.\n" +
                "Arma Racial:  possuem familiaridade com o arqueirismo recebendo um bônus de +1 nos danos causados em seus ataques à distância com os arcos.\n" +
                "Imunidade: imunes a efeitos ou magias que envolvam sono e também contra a paralisia causada por um Ghoul. ") {

    override fun exibirInfos() {
        println("Nome: $nome")
        println("Físico: $fisico")
        println("Habilidades: $habilidade")
    }
}
