package potatowonong.outboxpattern.domain.outbox.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import potatowonong.outboxpattern.domain.outbox.entity.OutboxEvent

interface OutboxEventRepository : JpaRepository<OutboxEvent, Long> {

    @Query(
        """
        SELECT oe FROM OutboxEvent oe
        WHERE oe.status = 'PENDING'
        ORDER BY oe.createdAt ASC
    """
    )
    fun findPendingEvents(): List<OutboxEvent>
}