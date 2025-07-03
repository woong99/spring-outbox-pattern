package potatowonong.outboxpattern.domain.order.repository

import org.springframework.data.jpa.repository.JpaRepository
import potatowonong.outboxpattern.domain.order.entity.OrderHistory

interface OrderHistoryRepository: JpaRepository<OrderHistory, Long> {
}