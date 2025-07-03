package potatowonong.outboxpattern.domain.product.entity

import jakarta.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    var quantity: Int
) {
    fun decreaseQuantity(amount: Int) {
        if (amount <= 0) {
            throw IllegalArgumentException("Amount to decrease must be greater than zero.")
        }
        if (quantity < amount) {
            throw IllegalStateException("Insufficient quantity to decrease by $amount.")
        }

        this.quantity -= amount
    }
}