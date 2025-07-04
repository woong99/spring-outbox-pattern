package potatowonong.outboxpattern.global.external.consumer

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderConsumer() {

    private val log = KotlinLogging.logger { }

    @KafkaListener(topics = ["CREATE-ORDER"], groupId = "outbox-pattern-group")
    fun orderListener(
        message: String
    ) {
        log.info { "[Kafka 메시지 도착] - Product Id : $message" }

        // 주문 처리 로직
    }
}