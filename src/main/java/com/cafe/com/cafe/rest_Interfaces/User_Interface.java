package com.cafe.com.cafe.rest_Interfaces;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe.com.cafe.wrapper.User_Wrapper;

@RequestMapping(path = "/user")
public interface User_Interface {

    
    // user signup api
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    // user login api
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    // get all user from db api
    @GetMapping(path = "/get")
    public ResponseEntity<List<User_Wrapper>> getAllUser();

    // update user from admoin said api
    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

    // check user token api
    @GetMapping(path = "/checkToken")
    public ResponseEntity<String> checkToken();

    // change password api
    @PostMapping(path = "/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody(required = true) Map<String, String> requestMap);

    // forgot password api
    @PostMapping(path = "/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody(required = true) Map<String, String> requestMap);

    // forgot password api
    @PutMapping(path = "/updateRole")
    public ResponseEntity<String> updateUserRole(@RequestBody(required = true) Map<String, String> requestMap);

    // forgot password api
    @PostMapping(path = "/sendOTP")
    public ResponseEntity<String> preSignup(@RequestBody(required = true) Map<String, String> requestMap);

    // forgot password api
    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteUser(@RequestBody(required = true) Map<String, Integer> requestMap);

}
