package com.cafe.com.cafe.service_Interfaces;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface User_Service_Interface {

    // user sign up api created in video-1
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    // user login api created in video-2
    ResponseEntity<String> login(Map<String, String> requestMap);

    
}
