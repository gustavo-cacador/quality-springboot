package br.com.gustavo.quality.business

import br.com.gustavo.quality.bean.Product
import br.com.gustavo.quality.bean.ProductCategory
import br.com.gustavo.quality.dtos.CategoryDTO
import br.com.gustavo.quality.dtos.ProductDTO
import br.com.gustavo.quality.repositories.CategoryRepository
import br.com.gustavo.quality.repositories.ProductCategoryRepository
import br.com.gustavo.quality.repositories.ProductRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductBusiness {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var productCategoryRepository: ProductCategoryRepository

    @GetMapping
    fun getAllProducts(
        @RequestParam(required = false) name: String?,
        pageable: Pageable): Page<ProductDTO> {
        val productPage: Page<Product> = if (!name.isNullOrBlank()) {
            productRepository.findByNameContainingIgnoreCase(name, pageable)
        } else {
            productRepository.findAll(pageable)
        }

        val productDtos: List<ProductDTO> = productPage.content.map { product ->
            val categories = categoryRepository.findCategoriesByProductId(product.id)
            val categoryDtos = categories.map { category ->
                CategoryDTO(id = category.id, name = category.name ?:"")
            }

            ProductDTO(
                id = product.id,
                name = product.name ?: "",
                description = product.description ?: "",
                price = product.price ?: 0.0,
                categories = categoryDtos
            )
        }
        return PageImpl(productDtos, pageable, productPage.totalElements)
 }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") id: Int): Product {
        return productRepository.findById(id).orElseThrow{EntityNotFoundException("Produto com id: $id não encontrado")}
    }


    @PostMapping
    fun postProduct(@RequestBody @Valid productDTO: ProductDTO): ProductDTO {
        val product = Product()
        product.name = productDTO.name
        product.description = productDTO.description
        product.price = productDTO.price

        val saveProduct = productRepository.save(product)

        productDTO.categories.forEach { categoryDTO ->
            val productCategory = ProductCategory().apply {
                this.productId = saveProduct.id
                this.categoryId = categoryDTO.id!!
            }
            productCategoryRepository.save(productCategory)
    }
        return productDTO.copy(id = saveProduct.id)
    }


    @PutMapping("/{id}")
    fun updateProduct(@PathVariable("id") id: Int, @RequestBody @Valid product: Product): Product {
        var productUpdate = productRepository.findById(id).orElseThrow{EntityNotFoundException("Produto com id: $id não encontrado")}

        productUpdate.name = product.name
        productUpdate.description = product.description
        productUpdate.price = product.price

        return productRepository.save(productUpdate)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable("id") id: Int) {
        var product = productRepository.findById(id).orElseThrow{EntityNotFoundException("Produto com id: $id não encontrado")}
        return productRepository.delete(product)
    }
}