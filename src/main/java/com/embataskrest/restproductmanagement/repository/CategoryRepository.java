package com.embataskrest.restproductmanagement.repository;

import com.embataskrest.restproductmanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select c from Category c where c.parentCategoryID is not null  and c.parentCategoryID<>2")
    List<Category> findAllSubCategories();
}
