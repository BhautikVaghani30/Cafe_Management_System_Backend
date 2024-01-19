package com.cafe.com.cafe.service_Interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafe.com.cafe.Entites.Category;

// this is category services interface class in video-5

public interface CategoryService {

    // adds new category to the category api
    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);
    // --------------------------------------------------------------------------------------------------------------
    ResponseEntity<List<Category>> getAllCategory(String filterValue);
    // --------------------------------------------------------------------------------------------------------------
    ResponseEntity<String> updateCategory(Map<String, String> requestMap);
}
