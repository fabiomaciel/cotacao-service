package io.github.fabiomaciel.cotacaoservice.common

import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
inline fun <T> timedExecution(id: String? = null, block: () -> T): T = measureTimedValue {
    block()
}.let {
    println("took ${it.duration} to execute function ${id ?: ""}")
    it.value
}