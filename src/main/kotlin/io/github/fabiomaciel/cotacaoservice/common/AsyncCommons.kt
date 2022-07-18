package io.github.fabiomaciel.cotacaoservice.common

import io.github.fabiomaciel.cotacaoservice.domain.model.Cotacao
import java.util.concurrent.CompletableFuture

fun executeAsync(id: String? = null, fn: () -> Cotacao?) =
    CompletableFuture.supplyAsync {
        fn()
    }