package io.github.fabiomaciel.cotacaoservice.domain.event.impl

import io.github.fabiomaciel.cotacaoservice.domain.event.CotacaoEventHandler
import io.github.fabiomaciel.cotacaoservice.web.controller.GetCotacaoCCallbackResquest
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit


@Component
class CotacaoEventHandlerFutureImpl : CotacaoEventHandler {
    private val events: MutableMap<String, CompletableFuture<GetCotacaoCCallbackResquest>> =
        mutableMapOf()


    override fun emit(id: String, event: GetCotacaoCCallbackResquest) {
        events.remove(id)?.complete(event)
    }

    override fun once(id: String): GetCotacaoCCallbackResquest? =
        CompletableFuture<GetCotacaoCCallbackResquest>().run {
            events[id] = this
            this.get(5, TimeUnit.SECONDS)
        }

    override fun once(id: String, callback: (GetCotacaoCCallbackResquest?) -> Unit) {
        callback(once(id))
    }
}