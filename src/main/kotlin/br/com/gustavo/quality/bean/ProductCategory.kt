package br.com.gustavo.quality.bean

import jakarta.persistence.*

@Entity
@Table(name = "produto_categoria", schema = "public")
class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "produto_id")
    var productId: Int? = null

    @Column(name = "categoria_id")
    var categoryId: Int? = null
}