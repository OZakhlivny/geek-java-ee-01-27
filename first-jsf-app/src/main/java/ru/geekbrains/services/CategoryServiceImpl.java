package ru.geekbrains.services;

import ru.geekbrains.dto.CategoryDto;
import ru.geekbrains.entities.Category;
import ru.geekbrains.repositories.CategoryRepository;
import ru.geekbrains.rest.CategoryServiceRest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService, CategoryServiceRest {

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id);
        if(category != null) return new CategoryDto(category);
        return null;
    }

    @Override
    public Long countAll() {
        return categoryRepository.countAll();
    }

    @Override
    public void insert(CategoryDto category) {
        if(category.getId() != null) throw new IllegalArgumentException();
        saveOrUpdate(category);
    }

    @Override
    public void update(CategoryDto category) {
        if(category.getId() == null) throw new IllegalArgumentException();
        saveOrUpdate(category);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(CategoryDto categoryDto) {
        categoryRepository.saveOrUpdate(new Category(categoryDto));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
