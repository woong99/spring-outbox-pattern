package potatowonong.outboxpattern.domain.scheduler

import org.springframework.stereotype.Component
import potatowonong.outboxpattern.domain.outbox.component.OrderOutboxProducer

@Component
class OutboxEventScheduler(
    private val orderOutboxProducer: OrderOutboxProducer
) {

//    @Async
//    @Scheduled(fixedDelay = 5000)
//    fun processOutboxEvents() {
//        orderOutboxProducer.sendCreateOrderMessage()
//    }
}