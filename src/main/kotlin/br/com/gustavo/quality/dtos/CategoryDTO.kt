package br.com.gustavo.quality.dtos

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CategoryDTO(
    var id: Int?,

    @field:NotBlank(message = "O nome do produto é obrigatório")
    @field:Size(min = 2, max = 80, message = "O nome tem que ser entre 2 e 80 caracteres")
    var name: String? = null
)
