package com.cafe.com.cafe.rest_Interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe.com.cafe.Entites.Category;

// this is category interface
// here also defaind api releted to category
@RequestMapping(path = "/category")
public interface CategoryRest {

    // --------------------------------------------------------------------------------------------------------------
    // add new category
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String, String> requestMap);
    
    // --------------------------------------------------------------------------------------------------------------
    // get all category
    @GetMapping(path = "/get")
    public ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = false) String filterValue); 

    // --------------------------------------------------------------------------------------------------------------
    // update category
    @PostMapping(path = "/update")
    public ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String, String> requestMap);

    
}
