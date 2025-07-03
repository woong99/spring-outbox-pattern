package potatowonong.outboxpattern.domain.order.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
}