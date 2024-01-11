package com.cafe.com.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class CafeUtils {

    private CafeUtils() {

    }

    // displays api response message video-1 start
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }
    // video-1 end


}
