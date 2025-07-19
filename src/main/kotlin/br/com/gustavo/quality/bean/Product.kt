package br.com.gustavo.quality.bean

import jakarta.persistence.*

@Entity
@Table(name = "produto", schema = "public")
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0

    @Column
    var name: String? = null

    @Column
    var description: String? = null

    @Column
    var price: Double? = null
}