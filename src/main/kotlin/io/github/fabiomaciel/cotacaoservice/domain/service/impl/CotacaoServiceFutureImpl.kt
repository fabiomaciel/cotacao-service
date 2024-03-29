package io.github.fabiomaciel.cotacaoservice.domain.service.impl

import io.github.fabiomaciel.cotacaoservice.common.executeAsync
import io.github.fabiomaciel.cotacaoservice.domain.event.CotacaoEventHandler
import io.github.fabiomaciel.cotacaoservice.domain.model.Cotacao
import io.github.fabiomaciel.cotacaoservice.domain.service.CotacaoService
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicea.ServiceAClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.serviceb.ServiceBClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.ServiceCClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.model.GetCotacaoCRequest
import io.github.fabiomaciel.cotacaoservice.web.controller.GetCotacaoCCallbackResquest
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class CotacaoServiceFutureImpl(
    val serviceAClient: ServiceAClient,
    val serviceBClient: ServiceBClient,
    val serviceCClient: ServiceCClient,
    val eventHandler: CotacaoEventHandler
) : CotacaoService {

    override fun buscarCotacao(moeda: String): Cotacao {
        val firstFuture = executeAsync {
            serviceAClient.getCotacao("USD").toCotacao()
        }

        val secondFuture = executeAsync {
            serviceBClient.getCotacao("USD").toCotacao()
        }

        val thirdFuture = executeAsync {
            val responseC = serviceCClient.getCotacao(GetCotacaoCRequest("USD"))
            eventHandler.once(responseC.cid)?.toCotacao()
        }

        val first = firstFuture.get()!!
        val second = secondFuture.get()!!
        val third = thirdFuture.get()

        println("requests: ${listOf(first, second, third)}")

        return first.orLesser(second).orLesser(third)
    }


    override fun callbackCotacao(callbackRequestBody: GetCotacaoCCallbackResquest) {
        eventHandler.emit(callbackRequestBody.cid, callbackRequestBody)
    }
}