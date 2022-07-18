package io.github.fabiomaciel.cotacaoservice.web.controller

import io.github.fabiomaciel.cotacaoservice.domain.model.Cotacao

data class GetCotacaoCCallbackResquest(
    val cid: String,
    val f: String,
    val t: String,
    val v: String
) {
    fun toCotacao(): Cotacao {
        return Cotacao(
            v.toDouble(),
            t,
            "BRL"
        )
    }
}
