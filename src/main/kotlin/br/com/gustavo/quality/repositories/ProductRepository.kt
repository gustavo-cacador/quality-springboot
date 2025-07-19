package br.com.gustavo.quality.repositories

import br.com.gustavo.quality.bean.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Int>{
}