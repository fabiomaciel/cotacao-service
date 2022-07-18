package io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicea

import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicea.model.GetCotacaoAResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("serviceA", url = "http://localhost:8081/servico-a")
interface ServiceAClient {

    @GetMapping("cotacao")
    fun getCotacao(@RequestParam moeda: String): GetCotacaoAResponse
}