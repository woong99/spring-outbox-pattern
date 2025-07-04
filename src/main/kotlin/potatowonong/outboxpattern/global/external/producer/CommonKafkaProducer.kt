package potatowonong.outboxpattern.global.external.producer

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import potatowonong.outboxpattern.domain.outbox.component.EventStatusUpdater
import potatowonong.outboxpattern.domain.outbox.entity.OutboxEvent

@Component
class CommonKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val eventStatusUpdater: EventStatusUpdater
) {
    private val log = KotlinLogging.logger { }

    fun sendOrderMessage(
        outboxEvent: OutboxEvent
    ) {
        kafkaTemplate.send(outboxEvent.topic, outboxEvent.aggregateId, outboxEvent.payload)
            .whenComplete { result, ex ->
                if (ex == null) {
                    // 이벤트 발송 성공
//                    eventStatusUpdater.markAsSent(outboxEvent)
                } else {
                    // 이벤트 발송 실패
                    eventStatusUpdater.markAsFailed(outboxEvent)
                    log.error(ex) { "Kafka 이벤트 발송 실패 - ${outboxEvent.id}" }
                }
            }
    }
}