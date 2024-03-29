package com.cafe.com.cafe.service_Interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafe.com.cafe.DTO.TransactionDetails;
import com.cafe.com.cafe.Entites.Bill;
import com.cafe.com.cafe.Entites.Order;

// this class created in 
public interface Bill_Service_interface {

    // --------------------------------------------------------------------------------------------------------------
    // produce bill of user
    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    // --------------------------------------------------------------------------------------------------------------
    // retrieves all bills api
    ResponseEntity<List<Bill>> getBills();

    // --------------------------------------------------------------------------------------------------------------
    // creates the pdf document for the bill api
    ResponseEntity<byte[]> getpdf(Map<String, Object> requestMap);

    // --------------------------------------------------------------------------------------------------------------
    // delete user bill
    ResponseEntity<String> deleteBill(Integer id);

    // --------------------------------------------------------------------------------------------------------------
    // make payment order
    TransactionDetails createTransaction(Double amount);

    // --------------------------------------------------------------------------------------------------------------
    // fetch all orders
    ResponseEntity<List<Order>> getAllorders(Map<String, String> requestMap);

    // --------------------------------------------------------------------------------------------------------------
    // update order status
    ResponseEntity<String> updateOrderStatus(Map<String, String> request);

    // --------------------------------------------------------------------------------------------------------------
    // delete orders
    ResponseEntity<String> deleteOrder(Integer id);

    // --------------------------------------------------------------------------------------------------------------
    // get orders According to category
    ResponseEntity<List<Order>> getOrderByCategory(Map<String, String> requestMap);

}
