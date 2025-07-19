package br.com.gustavo.quality

import br.com.gustavo.quality.bean.Product
import br.com.gustavo.quality.repositories.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class business {

    @Autowired
    lateinit var productRepository: ProductRepository

    @GetMapping
    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") id: Int): Product {
        return productRepository.findById(id).orElseThrow{EntityNotFoundException()}
    }

    @PostMapping
    fun postProduct(@RequestBody product: Product): Product {
        return productRepository.save(product)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable("id") id: Int, @RequestBody product: Product): Product {
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