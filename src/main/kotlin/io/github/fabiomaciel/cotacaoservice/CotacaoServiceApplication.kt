package io.github.fabiomaciel.cotacaoservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication(scanBasePackages = ["io.github.fabiomaciel.cotacaoservice"])
class CotacaoServiceApplication

fun main(args: Array<String>) {
    runApplication<CotacaoServiceApplication>(*args)
}
