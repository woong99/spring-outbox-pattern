package potatowonong.outboxpattern

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OutboxPatternApplication

fun main(args: Array<String>) {
    runApplication<OutboxPatternApplication>(*args)
}
