package potatowonong.outboxpattern.domain.order.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import potatowonong.outboxpattern.domain.product.repository.ProductRepository
import potatowonong.outboxpattern.global.external.producer.OrderProducer

@Service
class OrderService(
    private val productRepository: ProductRepository,
    private val orderProducer: OrderProducer
) {
    private val log = KotlinLogging.logger { }

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
}