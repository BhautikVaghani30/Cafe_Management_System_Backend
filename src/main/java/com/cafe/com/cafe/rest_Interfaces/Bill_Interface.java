package com.cafe.com.cafe.rest_Interfaces;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe.com.cafe.DTO.TransactionDetails;
import com.cafe.com.cafe.Entites.Bill;
import com.cafe.com.cafe.Entites.Order;

@RequestMapping(path = "/bill")
public interface Bill_interface {
    
    @PostMapping(path = "/generateReport")
    public ResponseEntity<String> generateReport(@RequestBody(required = true) Map<String, Object> requestMap);
    
    @GetMapping(path = "/getBills")
    public ResponseEntity<List<Bill>> getBills();
    
    @PostMapping(path = "/getpdf")
    public ResponseEntity<byte[]> getpdf(@RequestBody(required = true) Map<String ,Object> requestMap);
    
    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable("id") Integer id);

    @GetMapping(path = "/createTransaction/{amount}")
    public TransactionDetails createTransaction(@PathVariable("amount") Double amount);

    @GetMapping(path = "/getorders")
    public ResponseEntity<List<Order>> getAllOrders(Map<String,String> requestMap);

    @PutMapping(path = "/updateOrderStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestBody(required = true) Map<String,String> request);

    @PostMapping(path = "/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id);

    @PostMapping(path = "/getByCategory")
    public ResponseEntity<List<Order>> getOrderByCategory(@RequestBody(required = true) Map<String,String> requestMap);


    
}
