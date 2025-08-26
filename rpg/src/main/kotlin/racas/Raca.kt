package racas

abstract class Raca(
    val movimento: Int,
    val infravisao: Int,
    val alinhamento: String,
){
    abstract fun habilidades(): List<String>
}