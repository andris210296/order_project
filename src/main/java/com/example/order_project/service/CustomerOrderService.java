package com.example.order_project.service;

import com.example.order_project.dto.CustomerOrderRequestDto;
import com.example.order_project.dto.CustomerOrderResponseDto;
import com.example.order_project.entity.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {
    List<CustomerOrderResponseDto> getAllOrders();

    CustomerOrderResponseDto getOrderByIdDto(String id);

    CustomerOrder getOrderById(String id);

    CustomerOrder createOrder(CustomerOrderRequestDto order);


}
