package com.embataskrest.restproductmanagement.service;

import com.embataskrest.restproductmanagement.entity.Category;
import com.embataskrest.restproductmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAll(){
        List<Category>categoryList = new ArrayList<>();
        Iterable<Category>categoryIterable = categoryRepository.findAll();
        categoryIterable.forEach(categoryList::add);
        return categoryList;
    }

    public Category getCategoryById(int id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = null;
        if(optionalCategory.isPresent()){
            category = optionalCategory.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + " id-li category yoxdur");
        }
        return category;
    }

    public Category updateCategory(Category category){
        Optional<Category>optionalCategory = categoryRepository.findById(category.getCategoryID());
        if (optionalCategory.isPresent()){
            return categoryRepository.save(category);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, category.getCategoryID() + " id-li category yoxdur");
        }
    }

    public  void deleteCategory(int id){
        Optional<Category>optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + " id-li category yoxdur");
        }
    }

    public List<Category> getSubCategories(){
        return categoryRepository.findAllSubCategories();
    }
}
