package com.cafe.com.cafe.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.cafe.com.cafe.Entites.User;
import com.cafe.com.cafe.wrapper.User_Wrapper;

import jakarta.transaction.Transactional;

public interface User_Dao extends JpaRepository<User, Integer> {

    // video-2 start
    User findByEmailId(@Param("email") String email);
    // video-2 end

     // video-4 start
     List<User_Wrapper> getAllUser();
     // video-4 end

     // video-4 start
     @Transactional
     @Modifying
     Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
     // video-4 end


     List<String> getAllAdmin();
    

}
