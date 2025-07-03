package potatowonong.outboxpattern.global.external.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {
    fun sendOrderMessage(
        message: Any
    ) {
        kafkaTemplate.send("order", message).get()
    }
}