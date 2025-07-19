package br.com.gustavo.quality.repositories

import br.com.gustavo.quality.bean.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CategoryRepository : JpaRepository<Category, Int> {

    @Query(
        value = """
            SELECT c.* FROM categoria c
            INNER JOIN produto_categoria pc ON c.id = pc.categoria_id
            WHERE pc.produto_id = :productId
        """,
        nativeQuery = true
    )
    fun findCategoriesByProductId(@Param("productId") productId: Int): List<Category>
}