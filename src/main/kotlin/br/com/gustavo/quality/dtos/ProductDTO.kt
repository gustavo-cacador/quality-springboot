package br.com.gustavo.quality.dtos

data class ProductDTO(
    val id: Int?,
    val name: String,
    val description: String,
    val price: Double,
    val categories: List<CategoryDTO>
)
