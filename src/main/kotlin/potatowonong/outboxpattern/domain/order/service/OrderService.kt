package potatowonong.outboxpattern.domain.order.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import potatowonong.outboxpattern.domain.outbox.entity.OutboxEvent
import potatowonong.outboxpattern.domain.outbox.repository.OutboxEventRepository
import potatowonong.outboxpattern.domain.product.repository.ProductRepository
import potatowonong.outboxpattern.global.external.producer.OrderProducer
import java.util.*

@Service
class OrderService(
    private val productRepository: ProductRepository,
    private val outboxEventRepository: OutboxEventRepository,
    private val orderProducer: OrderProducer
) {
    @Transactional
    fun createOrder(
        productId: Long
    ) {
        // 상품 정보 조회
        val product = productRepository.findByIdOrNull(productId)
            ?: throw IllegalArgumentException("Product with id $productId not found")

        // 주문 기록 생성(Kafka)
        orderProducer.sendOrderMessage(product.id!!.toString())

        // 재고 감소
        product.decreaseQuantity(10)
        throw IllegalStateException()
    }

    @Transactional
    fun createOrderWithOutboxPattern(
        productId: Long
    ) {
        // 상품 정보 조회
        val product = productRepository.findByIdOrNull(productId)
            ?: throw IllegalArgumentException("Product with id $productId not found")

        // 주문 기록 생성(Outbox)
        outboxEventRepository.save(
            OutboxEvent.createOrder(
                payload = product.id!!.toString(),
                aggregateId = UUID.randomUUID().toString(),
            )
        )

        // 재고 감소
        product.decreaseQuantity(10)
    }
}