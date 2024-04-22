package com.example.jobpostingboard_api.service;


import com.example.jobpostingboard_api.dto.CategoryDto;
import com.example.jobpostingboard_api.entity.Category;
import com.example.jobpostingboard_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Category addNewCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        return category;

    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category updateCategory(CategoryDto categoryDto, int id) {

        var category = getById(id);

        category.setDescription(categoryDto.getDescription());
        category.setName(category.getName());
        categoryRepository.save(category);
        return category;
    }

    public String deleteById(int id) {
      var result = getById(id);
      if(result!=null){
          categoryRepository.deleteById(id);
          return "Category deleted succesfully";
      }
      else{
          return null;
      }
    }


    public Category getCategoryByName(String name){
        return categoryRepository.findByname(name);
    }
}
