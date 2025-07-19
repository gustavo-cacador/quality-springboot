package br.com.gustavo.quality.business

import br.com.gustavo.quality.bean.Category
import br.com.gustavo.quality.repositories.CategoryRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("categories")
class CategoryBusiness {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @GetMapping
    fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getCategory(@PathVariable("id") id: Int): Category {
        return categoryRepository.findById(id).orElseThrow{ EntityNotFoundException("Categoria com id: $id não encontrado") }
    }

    @PostMapping
    fun postCategory(@RequestBody category: Category): Category {
        return categoryRepository.save(category)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable("id") id: Int, @RequestBody category: Category): Category {
        var categoryUpdate = categoryRepository.findById(id).orElseThrow{EntityNotFoundException("Categoria com id: $id não encontrado")}

        categoryUpdate.name = category.name

        return categoryRepository.save(categoryUpdate)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable("id") id: Int) {
        var category = categoryRepository.findById(id).orElseThrow{EntityNotFoundException("Categoria com id: $id não encontrado")}
        return categoryRepository.delete(category)
    }
}