package org.wifry.fooddelivery.services.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.wifry.fooddelivery.model.Category;
import org.wifry.fooddelivery.model.Status;
import org.wifry.fooddelivery.repository.admin.CategoryRepository;
import org.wifry.fooddelivery.services.admin.CategoryService;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getByID(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> list() {
        Example<Category> example = Example.of(new Category(Status.ACTIVO));
        return categoryRepository.findAll(example);
    }

    @Override
    public List<Category> find(String valor) {
        return categoryRepository.findCategory(valor);
    }

    @Override
    public void save(Category entity) {
        categoryRepository.save(entity);
    }

    @Override
    public void delete(Category entity) {
        categoryRepository.delete(entity);
    }

    @Override
    public void updateState(Category entity) {
        categoryRepository.updateState(entity);
    }


}
