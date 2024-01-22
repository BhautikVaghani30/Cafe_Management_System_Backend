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
    public ResponseEntity<String> updateProduct(@RequestBody Map<String, String> requstMap);

    // this is delete product api video-7
    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id);

    // this is update product status api video-7
    @PostMapping(path = "/updatestatus")
    public ResponseEntity<String> updateStatus(@RequestBody Map<String, String> requestMap);

    // this is fetch product by category api video-7
    @GetMapping(path = "/getbycategory/{id}")
    public ResponseEntity<List<Product_Wrapper>> getByCategory(@PathVariable Integer id);

    // this is get product by id api video-7
    @GetMapping(path = "/getbyid/{id}")
    public ResponseEntity<Product_Wrapper> getById(@PathVariable Integer id);

}
