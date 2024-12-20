package com.example.order_project.service.impl;

import com.example.order_project.dto.CustomerOrderRequestDto;
import com.example.order_project.dto.CustomerOrderResponseDto;
import com.example.order_project.dto.OrderItemResponseDto;
import com.example.order_project.dto.ProductResponseDto;
import com.example.order_project.entity.CustomerOrder;
import com.example.order_project.exception.OrderNotCreatedException;
import com.example.order_project.repository.CustomerOrderRepository;
import com.example.order_project.service.CustomerOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerOrderServiceImpl.class);

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public List<CustomerOrderResponseDto> getAllOrders() {
        logger.info("Fetching all customer orders");

        List<CustomerOrderResponseDto> orders = customerOrderRepository.findAll().stream()
                .map(order -> CustomerOrderResponseDto.builder()
                        .id(order.getId())
                        .orderDate(order.getOrderDate())
                        .total(order.getTotal())
                        .orderItemResponseDtoList(order.getOrderItems().stream()
                                .map(orderItem -> OrderItemResponseDto.builder()
                                        .quantity(orderItem.getQuantity())
                                        .product(ProductResponseDto.builder()
                                                .name(orderItem.getProduct().getName())
                                                .price(orderItem.getProduct().getPrice())
                                                .description(orderItem.getProduct().getDescription())
                                                .build())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        logger.debug("Fetched {} customer orders", orders.size());
        logger.info("Fetched customer orders successfully");
        return orders;
    }

    @Override
    public CustomerOrderResponseDto getOrderByIdDto(String id) {
        logger.info("Fetching customer order by ID");
        logger.debug("Fetching customer order with ID: {}", id);

        CustomerOrder order = findCustomerOrderById(id);
        CustomerOrderResponseDto responseDto = CustomerOrderResponseDto.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .total(order.getTotal())
                .orderItemResponseDtoList(order.getOrderItems().stream()
                        .map(orderItem -> OrderItemResponseDto.builder()
                                .product(ProductResponseDto.builder()
                                        .name(orderItem.getProduct().getName())
                                        .price(orderItem.getProduct().getPrice())
                                        .description(orderItem.getProduct().getDescription())
                                        .build())
                                .quantity(orderItem.getQuantity())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        logger.debug("Fetched customer order: {}", responseDto);
        logger.info("Fetched customer order by id successfully");
        return responseDto;
    }

    @Override
    public CustomerOrder getOrderById(String id) {
        logger.info("Fetching customer order entity by ID");
        logger.debug("Fetching customer order entity with ID: {}", id);

        return findCustomerOrderById(id);
    }

    @Override
    public CustomerOrder createOrder(CustomerOrderRequestDto orderDto) {
        logger.info("Creating customer order");
        logger.debug("Creating customer order with details: {}", orderDto);

        LocalDate orderDate = orderDto.getOrderDate() != null ? orderDto.getOrderDate() : LocalDate.now();
        CustomerOrder order = CustomerOrder.builder()
                .orderDate(orderDate)
                .orderItems(orderDto.getOrderItems())
                .build();
        CustomerOrder savedOrder = customerOrderRepository.save(order);

        logger.info("Created customer order successfully");
        logger.debug("Created customer order: {}", savedOrder);

        return savedOrder;
    }

    private CustomerOrder findCustomerOrderById(String id) {
        logger.info("Finding customer order by ID");
        logger.debug("Finding customer order with ID: {}", id);

        return customerOrderRepository.findById(id)
                .orElseThrow(() -> new OrderNotCreatedException("The order with the provided ID does not exist"));
    }
}
