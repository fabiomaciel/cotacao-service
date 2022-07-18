package io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec

import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.model.GetCotacaoCRequest
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.model.GetCotacaoCResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient("serviceC", url = "http://localhost:8081/servico-c")
interface ServiceCClient {

    @PostMapping("cotacao")
    fun getCotacao(@RequestBody body: GetCotacaoCRequest): GetCotacaoCResponse
}