package io.github.fabiomaciel.cotacaoservice.infra.httpclient.serviceb

import io.github.fabiomaciel.cotacaoservice.infra.httpclient.serviceb.model.GetCotacaoBResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("serviceB", url = "http://localhost:8081/servico-b")
interface ServiceBClient {

    @GetMapping("cotacao")
    fun getCotacao(@RequestParam curr: String): GetCotacaoBResponse
}