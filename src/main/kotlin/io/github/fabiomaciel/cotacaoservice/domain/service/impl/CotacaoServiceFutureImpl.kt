package io.github.fabiomaciel.cotacaoservice.domain.service.impl

import io.github.fabiomaciel.cotacaoservice.domain.event.CotacaoEventHandler
import io.github.fabiomaciel.cotacaoservice.domain.model.Cotacao
import io.github.fabiomaciel.cotacaoservice.domain.service.CotacaoService
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicea.ServiceAClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.serviceb.ServiceBClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.ServiceCClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.model.GetCotacaoCRequest
import io.github.fabiomaciel.cotacaoservice.web.controller.GetCotacaoCCallbackResquest
import org.springframework.stereotype.Component

@Component
class CotacaoServiceFutureImpl(
    val serviceAClient: ServiceAClient,
    val serviceBClient: ServiceBClient,
    val serviceCClient: ServiceCClient,
    val eventHandler: CotacaoEventHandler
) : CotacaoService {

    override fun buscarCotacao(moeda: String): Cotacao {
        val first = serviceAClient.getCotacao("USD").toCotacao()
        val second = serviceBClient.getCotacao("USD").toCotacao()
        val responseC = serviceCClient.getCotacao(GetCotacaoCRequest("USD"))
        val third = eventHandler.once(responseC.cid)?.toCotacao()

        println("first: $first")
        println("second: $second")
        println("third: $third")

        return first.orLesser(second).orLesser(third)
    }

    override fun callbackCotacao(callbackRequestBody: GetCotacaoCCallbackResquest) {
        eventHandler.emit(callbackRequestBody.cid, callbackRequestBody)
    }
}