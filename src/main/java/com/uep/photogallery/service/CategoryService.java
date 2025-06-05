package com.uep.photogallery.service;

import com.uep.photogallery.model.Category;
import com.uep.photogallery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category createCategory(String name, String description) {
        // Check if category with this name already exists
        if (categoryRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Category with this name already exists");
        }
        
        Category category = new Category(name, description);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> searchCategories(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    public Category updateCategory(Long id, String name, String description) {
        return categoryRepository.findById(id)
            .map(category -> {
                category.setName(name);
                category.setDescription(description);
                return categoryRepository.save(category);
            })
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
} 