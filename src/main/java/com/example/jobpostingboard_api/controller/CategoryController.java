package com.example.jobpostingboard_api.controller;


import com.example.jobpostingboard_api.dto.CategoryDto;
import com.example.jobpostingboard_api.entity.Category;
import com.example.jobpostingboard_api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.addNewCategory(categoryDto));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PutMapping("/{id}")
        public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody CategoryDto categoryDto ){
            return ResponseEntity.ok(categoryService.updateCategory(categoryDto,id));
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id){
        var result = categoryService.deleteById(id);
        if(result!=null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

}
