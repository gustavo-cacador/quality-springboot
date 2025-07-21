package br.com.gustavo.quality.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class ProductDTO(
    val id: Int?,

    @field:NotBlank(message = "O nome do produto é obrigatório")
    @field:Size(min = 2, max = 80, message = "O nome tem que ser entre 2 e 80 caracteres")
    val name: String,

    @field:NotBlank(message = "A descrição do produto é obrigatório")
    @field:Size(min = 5, max = 250, message = "A descrição tem que ser entre 5 e 250 caracteres")
    val description: String,

    @field:NotNull(message = "O preço não pode ser nulo")
    @field:Positive(message = "O preço precisa ser positivo")
    val price: Double,

    @field:NotEmpty(message = "Deve ter pelo menos uma categoria")
    val categories: List<CategoryDTO>
)
