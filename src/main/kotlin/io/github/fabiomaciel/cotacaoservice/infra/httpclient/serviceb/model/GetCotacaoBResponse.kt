package io.github.fabiomaciel.cotacaoservice.infra.httpclient.serviceb.model

import io.github.fabiomaciel.cotacaoservice.domain.model.Cotacao

data class GetCotacaoBResponse(
    val cotacao: GetCotacaoBDataResponse
) {
    fun toCotacao() = Cotacao(
        cotacao.valor.toDouble(),
        cotacao.currency,
        "BRL"
    )
}
