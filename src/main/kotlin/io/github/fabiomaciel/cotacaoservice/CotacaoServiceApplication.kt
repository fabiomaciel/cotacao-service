package io.github.fabiomaciel.cotacaoservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CotacaoServiceApplication

fun main(args: Array<String>) {
    runApplication<CotacaoServiceApplication>(*args)
}
