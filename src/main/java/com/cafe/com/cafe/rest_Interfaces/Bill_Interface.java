package com.cafe.com.cafe.rest_Interfaces;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe.com.cafe.Entites.Bill;

// this class is creatde in video - 9
@RequestMapping(path = "/bill")
public interface Bill_interface {

    // this api created in video-9
    @PostMapping(path = "/generateReport")
    public ResponseEntity<String> generateReport(@RequestBody(required = true) Map<String, Object> requestMap);
    
    // this api created in video-10
    @GetMapping(path = "/getBills")
    public ResponseEntity<List<Bill>> getBills();
    
    // this api created in video-10
    @PostMapping(path = "/getpdf")
    public ResponseEntity<byte[]> getpdf(@RequestBody(required = true) Map<String ,Object> requestMap);
    
    // this api created in video-10
    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable("id") Integer id);
}
