package com.embataskrest.restproductmanagement.controller;

import com.embataskrest.restproductmanagement.entity.Category;
import com.embataskrest.restproductmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public Category getByCategoryId(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, Category category){
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }

    @GetMapping("/all")
    public List<Category> getAll(){
        return categoryService.getAll();
    }
}
