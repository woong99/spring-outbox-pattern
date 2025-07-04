package potatowonong.outboxpattern.domain.outbox.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import potatowonong.outboxpattern.domain.outbox.enums.OutboxStatus
import java.time.LocalDateTime

@Entity
class OutboxEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    @Comment("어그리게이트 타입")
    val aggregateType: String,

    @Column(nullable = false)
    @Comment("어그리게이트 ID")
    val aggregateId: String,
 
    @Column(nullable = false)
    @Comment("이벤트 타입")
    val eventType: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    @Comment("이벤트 페이로드")
    val payload: String,

    @Column(nullable = false)
    @Comment("이벤트가 발생한 토픽")
    val topic: String,

    @Column(nullable = false)
    @Comment("재시도 횟수")
    val retryCount: Int = 0,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("이벤트 상태")
    var status: OutboxStatus = OutboxStatus.PENDING,

    @Column(nullable = false)
    @Comment("이벤트 생성 시간")
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        fun createOrder(
            payload: String,
            aggregateId: String,
        ) = OutboxEvent(
            aggregateType = "ORDER",
            aggregateId = aggregateId,
            eventType = "ORDER_CREATED",
            payload = payload,
            topic = "CREATE-ORDER",
        )
    }

    fun markAsFailed() {
        if (status != OutboxStatus.PENDING) {
            throw IllegalStateException("Only pending events can be marked as failed.")
        }
        status = OutboxStatus.FAILED
    }

    fun markAsSent() {
        if (status != OutboxStatus.PENDING) {
            throw IllegalStateException("Only pending events can be marked as processed.")
        }
        status = OutboxStatus.SENT
    }
}