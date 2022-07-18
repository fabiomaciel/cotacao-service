package io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.model

data class GetCotacaoCRequest(
    val tipo: String,
    val callback: String = "http://host.docker.internal:8080/cotacoes/callback"
)
