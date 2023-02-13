package com.bctech.fashionista.repository;

import com.bctech.fashionista.dto.response.PostResponseDto;
import com.bctech.fashionista.entity.Admin;
import com.bctech.fashionista.entity.Categories;
import com.bctech.fashionista.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {


//      List<Post> findAllByCategories_Title(String categoryTitle);

      Page<Post> findAllByCategories_Title(String categoryTitle, Pageable pageable);

}