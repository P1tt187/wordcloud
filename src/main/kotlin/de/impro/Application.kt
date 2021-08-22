package de.impro

import io.micronaut.runtime.Micronaut.*
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*

@OpenAPIDefinition(info = Info(title = "suggestor1", version = "1")) object Api {}

fun main(args: Array<String>) {
  build().args(*args).packages("de.impro").start()
}
