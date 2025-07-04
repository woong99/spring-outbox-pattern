package potatowonong.outboxpattern.domain.outbox.component

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import potatowonong.outboxpattern.domain.outbox.repository.OutboxEventRepository
import potatowonong.outboxpattern.global.external.producer.CommonKafkaProducer

@Component
class OrderOutboxProducer(
    private val outboxEventRepository: OutboxEventRepository,
    private val commonKafkaProducer: CommonKafkaProducer
) {
    @Transactional(readOnly = true)
    fun sendCreateOrderMessage() {
        val outboxEvents = outboxEventRepository.findPendingEvents()
        outboxEvents.forEach { event ->
            commonKafkaProducer.sendOrderMessage(event)
        }
    }
}