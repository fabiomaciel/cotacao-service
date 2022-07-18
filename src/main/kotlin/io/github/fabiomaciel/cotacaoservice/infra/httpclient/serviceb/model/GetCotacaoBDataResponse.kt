package io.github.fabiomaciel.cotacaoservice.infra.httpclient.serviceb.model

data class GetCotacaoBDataResponse(
    val fator: Int,
    val currency: String,
    val valor: String
)
