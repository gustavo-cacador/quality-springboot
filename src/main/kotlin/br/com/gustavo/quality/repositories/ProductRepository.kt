package br.com.gustavo.quality.repositories

import br.com.gustavo.quality.bean.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Int>{

    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Product>
}