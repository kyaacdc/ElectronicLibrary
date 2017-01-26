package com.el.spring.service.implementations;

import com.el.spring.entity.Category;
import com.el.spring.repository.CategoryRepository;
import com.el.spring.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addItem(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category editItem(Category category) {
        return addItem(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public Category find(Integer id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
