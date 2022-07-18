package io.github.fabiomaciel.cotacaoservice.web.controller

import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicea.ServiceAClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.serviceb.ServiceBClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.ServiceCClient
import io.github.fabiomaciel.cotacaoservice.infra.httpclient.servicec.model.GetCotacaoCRequest
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    val serviceAClient: ServiceAClient,
    val serviceBClient: ServiceBClient,
    val serviceCClient: ServiceCClient
) {

    private val callbackResponses: MutableMap<String, CompletableDeferred<GetCotacaoCCallbackResquest>> = mutableMapOf()

    fun test() = runBlocking {

    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @GetMapping("/")
    fun hello(): Triple<Any, Any, Any?> = runBlocking {
        val first = serviceAClient.getCotacao("USD")
        val second = serviceBClient.getCotacao("USD")
        val response = serviceCClient.getCotacao(GetCotacaoCRequest("USD"))
        var third: GetCotacaoCCallbackResquest? = null
        val deferred = CompletableDeferred<GetCotacaoCCallbackResquest>().apply {
            invokeOnCompletion {
                third = this.getCompleted()
            }
        }
        callbackResponses[response.cid] = deferred
        deferred.await()
        Triple(
            first,
            second,
            third
        )
    }


    @PostMapping("/callback")
    fun callback(@RequestBody body: GetCotacaoCCallbackResquest) {
        callbackResponses[body.cid]?.complete(body)
        println("----------------------------")
        println(body)
        println("----------------------------")
    }
}