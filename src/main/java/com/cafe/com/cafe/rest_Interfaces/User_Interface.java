package com.cafe.com.cafe.rest_Interfaces;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/user")
public interface User_Interface {

    // video-1 start
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true)Map<String, String> requestMap);
    // video-1 end
    
    // video-1
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true)Map<String, String> requestMap);
    // video-2

}

