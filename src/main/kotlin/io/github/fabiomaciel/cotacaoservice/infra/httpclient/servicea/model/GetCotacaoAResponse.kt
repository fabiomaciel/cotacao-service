package io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicea.model

data class GetCotacaoAResponse(
    val cotacao: Double,
    val moeda: String,
    val symbol: String
)
