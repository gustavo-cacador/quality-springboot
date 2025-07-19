package br.com.gustavo.quality.bean

import jakarta.persistence.*

@Entity
@Table(name = "categoria", schema = "public")
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0

    @Column
    var name: String? = null

}