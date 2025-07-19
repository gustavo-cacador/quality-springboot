package br.com.gustavo.quality.repositories

import br.com.gustavo.quality.bean.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository

interface ProductCategoryRepository : JpaRepository<ProductCategory, Int> {
}