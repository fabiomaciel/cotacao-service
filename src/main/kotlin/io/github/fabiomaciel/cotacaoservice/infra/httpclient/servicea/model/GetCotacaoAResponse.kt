package io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicea.model

import io.github.fabiomaciel.cotacaoservice.domain.model.Cotacao

data class GetCotacaoAResponse(
    val cotacao: Double,
    val moeda: String,
    val symbol: String
) {
    fun toCotacao(): Cotacao {
        return Cotacao(
            cotacao,
            moeda,
            "BRL"
        )
    }
}
