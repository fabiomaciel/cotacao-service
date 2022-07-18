package io.github.fabiomaciel.cotacaoservice.domain.event

import io.github.fabiomaciel.cotacaoservice.web.controller.GetCotacaoCCallbackResquest

interface CotacaoEventHandler {

    fun emit(id: String, event: GetCotacaoCCallbackResquest)

    fun once(id: String, callback: (GetCotacaoCCallbackResquest?) -> Unit)

    fun once(id: String): GetCotacaoCCallbackResquest?

}