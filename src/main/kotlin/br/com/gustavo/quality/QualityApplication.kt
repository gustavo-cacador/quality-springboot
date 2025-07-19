package br.com.gustavo.quality

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QualityApplication

fun main(args: Array<String>) {
	runApplication<QualityApplication>(*args)
}
