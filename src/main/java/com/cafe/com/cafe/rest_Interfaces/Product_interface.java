package com.cafe.com.cafe.rest_Interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cafe.com.cafe.wrapper.Product_Wrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/product")
public interface Product_interface {

    // this is add product api video-6
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewProduct(@RequestBody(required = true) Map<String, String> requestMap);

    // this is get all product api video-6
    @GetMapping(path = "/get")
    public ResponseEntity<List<Product_Wrapper>> getAllProduct();

    // this is update product api video-6
    @PostMapping(path = "/update")
    public ResponseEntity<String> updateProduct(@RequestBody Map<String,String> requstMap);
}



