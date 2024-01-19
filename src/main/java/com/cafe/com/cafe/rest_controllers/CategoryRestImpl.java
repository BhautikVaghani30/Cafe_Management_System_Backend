package com.cafe.com.cafe.rest_controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.com.cafe.Entites.Category;
import com.cafe.com.cafe.constants.Cafe_Constants;
import com.cafe.com.cafe.rest_Interfaces.CategoryRest;
import com.cafe.com.cafe.service_Interfaces.CategoryService;
import com.cafe.com.cafe.utils.CafeUtils;

@RestController
public class CategoryRestImpl implements CategoryRest {

    // --------------------------------------------------------------------------------------------------------------
    @Autowired
    CategoryService categoryService;

    // --------------------------------------------------------------------------------------------------------------
    // video-5
    // add new category
    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            return categoryService.addNewCategory(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // --------------------------------------------------------------------------------------------------------------
    // video-5
    // get all category
    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try {
            return this.categoryService.getAllCategory(filterValue);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // --------------------------------------------------------------------------------------------------------------
    // video-5
    // update category
    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
       try {
        return this.categoryService.updateCategory(requestMap);
       } catch (Exception e) {
        e.printStackTrace();
       }
       return CafeUtils.getResponseEntity(Cafe_Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
