package io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.model

data class GetCotacaoCResponse(
    val mood: String,
    val cid: String,
    val mensagem: String
)
