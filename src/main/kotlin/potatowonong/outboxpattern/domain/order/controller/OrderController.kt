package potatowonong.outboxpattern.domain.order.controller

import org.springframework.web.bind.annotation.*
import potatowonong.outboxpattern.domain.order.service.OrderService

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping("/{productId}")
    fun createOrder(
        @PathVariable productId: Long
    ) {
        orderService.createOrder(productId)
    }

    @PostMapping("/{productId}/outbox-pattern")
    fun createOrderWithOutboxPattern(
        @PathVariable productId: Long,
        @RequestParam payload: String
    ) {
        orderService.createOrderWithOutboxPattern(productId, payload)
    }
}