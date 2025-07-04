package potatowonong.outboxpattern.domain.outbox.component

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import potatowonong.outboxpattern.domain.outbox.entity.OutboxEvent
import potatowonong.outboxpattern.domain.outbox.repository.OutboxEventRepository

@Component
class EventStatusUpdater(
    private val outboxEventRepository: OutboxEventRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun markAsSent(
        event: OutboxEvent
    ) {
        event.markAsSent()
        outboxEventRepository.save(event)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun markAsFailed(
        event: OutboxEvent
    ) {
        event.markAsFailed()
        outboxEventRepository.save(event)
    }
}