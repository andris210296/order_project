package com.example.order_project.controller;

import com.example.order_project.dto.CustomerOrderRequestDto;
import com.example.order_project.dto.CustomerOrderResponseDto;
import com.example.order_project.entity.CustomerOrder;
import com.example.order_project.service.CustomerOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management", description = "APIs for managing orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieve a list of all orders")
    public ResponseEntity<List<CustomerOrderResponseDto>> getAllOrders() {
        return ResponseEntity.ok(customerOrderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieve an order by its ID")
    public ResponseEntity<CustomerOrderResponseDto> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(customerOrderService.getOrderByIdDto(id));
    }

    @PostMapping
    @Operation(summary = "Create a new order", description = "Create a new order with the provided details")
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody @Valid CustomerOrderRequestDto orderDto) {
        return ResponseEntity.ok(customerOrderService.createOrder(orderDto));
    }
}
