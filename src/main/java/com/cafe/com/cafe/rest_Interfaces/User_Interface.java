package com.cafe.com.cafe.rest_Interfaces;

import java.util.Map;
import  java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe.com.cafe.wrapper.User_Wrapper;

@RequestMapping(path = "/user")
public interface User_Interface {

    // video-2 start
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true)Map<String, String> requestMap);
    // video-2 end
    
    // video-3
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true)Map<String, String> requestMap);
    // video-3

    // video-4
    @GetMapping(path = "/get")
    public ResponseEntity<List<User_Wrapper>> getAllUser();  

    // video-4
    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String,String> requestMap) ;  

}

