package com.cafe.com.cafe.dao;

import com.cafe.com.cafe.Entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// this is category repository 
// video-5

public interface CategoryDao extends JpaRepository<Category, Integer> {
    List<Category> getAllCategory();
}
