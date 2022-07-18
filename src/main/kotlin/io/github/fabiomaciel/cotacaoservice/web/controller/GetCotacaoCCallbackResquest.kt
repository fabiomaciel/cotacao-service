package io.github.fabiomaciel.cotacaoservice.web.controller

data class GetCotacaoCCallbackResquest(
    val cid: String,
    val f: String,
    val t: String,
    val v: String
)
