package com.bctech.fashionista.repository;

import com.bctech.fashionista.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    Optional<Categories> findByTitle(String title);

    List<Categories> findCategoriesByPostsContains(Long id);
}