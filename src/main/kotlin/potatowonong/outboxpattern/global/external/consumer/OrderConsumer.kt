package potatowonong.outboxpattern.global.external.consumer

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class OrderConsumer(
    private val redisTemplate: RedisTemplate<String, String>
) {

    private val log = KotlinLogging.logger { }

    @KafkaListener(topics = ["CREATE-ORDER"], groupId = "outbox-pattern-group")
    fun orderListener(
        message: String,
        @Header("kafka_receivedMessageKey") key: String
    ) {
        if (redisTemplate.opsForValue().setIfAbsent("order-event:$key", "1", Duration.ofHours(1)) == true) {
            log.info { "[Kafka 메시지 도착] - Product Id : $message, Key : $key" }

            // 주문 처리 로직
        } else {
            log.warn { "[중복 메시지 수신] - Product Id : $message, Key : $key" }
        }
    }
}