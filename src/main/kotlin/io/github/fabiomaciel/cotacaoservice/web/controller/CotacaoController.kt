package io.github.fabiomaciel.cotacaoservice.web.controller

import io.github.fabiomaciel.cotacaoservice.domain.model.Cotacao
import io.github.fabiomaciel.cotacaoservice.domain.service.CotacaoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("cotacoes")
class CotacaoController(
    val cotacaoService: CotacaoService
) {

    @GetMapping()
    fun hello(@RequestParam moeda: String): Cotacao {
        return cotacaoService.buscarCotacao(moeda)
    }

    @PostMapping("/callback")
    fun callback(@RequestBody body: GetCotacaoCCallbackResquest) {
        cotacaoService.callbackCotacao(body)
    }
}