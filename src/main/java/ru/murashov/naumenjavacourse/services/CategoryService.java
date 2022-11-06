package ru.murashov.naumenjavacourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Category;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.models.Product;
import ru.murashov.naumenjavacourse.repositories.CategoryRepository;
import ru.murashov.naumenjavacourse.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }


    public Category getCategory(int id){
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }

    public void updateCategory(int id, Category updatedCategory){
        Category categoryToBeUpdated = categoryRepository.findById(id).get();
        categoryToBeUpdated.setName(updatedCategory.getName());
    }
}
