package io.github.fabiomaciel.cotacaoservice.domain.model

data class Cotacao(
    val cotacao: Double,
    val moeda: String,
    val comparativo: String
) {
    fun orLesser(other: Cotacao?): Cotacao {
        return if (other != null && other.cotacao < this.cotacao) other else this
    }
}
