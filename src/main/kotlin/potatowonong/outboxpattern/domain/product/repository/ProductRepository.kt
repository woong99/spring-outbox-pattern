package potatowonong.outboxpattern.domain.product.repository

import org.springframework.data.jpa.repository.JpaRepository
import potatowonong.outboxpattern.domain.product.entity.Product

interface ProductRepository: JpaRepository<Product, Long> {
}