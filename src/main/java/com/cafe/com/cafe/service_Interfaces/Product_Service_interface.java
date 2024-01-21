package com.cafe.com.cafe.service_Interfaces;

import org.springframework.http.ResponseEntity;

import com.cafe.com.cafe.wrapper.Product_Wrapper;

import java.util.List;
import java.util.Map;

public interface Product_Service_interface {

    // adds new product to a category api
    // video-6
    ResponseEntity<String> addNewProduct(Map<String, String> requestMap);
    
    // get all product from database
    // video-6
    ResponseEntity<List<Product_Wrapper>> getAllProduct();

    // update the product in db 
    // video-6
    ResponseEntity<String> updateProduct(Map<String,String> requstMap);

}
