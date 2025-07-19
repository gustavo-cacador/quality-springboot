package br.com.gustavo.quality.repositories

import br.com.gustavo.quality.bean.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Int> {
}