package com.cafe.com.cafe.service_Interfaces;

import org.springframework.http.ResponseEntity;
import com.cafe.com.cafe.wrapper.*;
import java.util.*;

public interface User_Service_Interface {

    // user sign up api created in video-2
    ResponseEntity<String> signUp(Map<String, String> requestMap);

    // user login api created in video-3
    ResponseEntity<String> login(Map<String, String> requestMap);

    // user login api created in video-4
    ResponseEntity<List<User_Wrapper>> getAllUser();

    // user login api created in video-4
    ResponseEntity<String> update(Map<String, String> requestMap);

    // user tokencheck v-5
    ResponseEntity<String> checkToken();

    // change password api v-5
    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    // forgot password api v-5
    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);

}
