package io.github.fabiomaciel.cotacaoservice.domain.service

import io.github.fabiomaciel.cotacaoservice.domain.model.Cotacao
import io.github.fabiomaciel.cotacaoservice.web.controller.GetCotacaoCCallbackResquest

interface CotacaoService {
    fun buscarCotacao(moeda: String): Cotacao

    fun callbackCotacao(callbackRequestBody: GetCotacaoCCallbackResquest)
}