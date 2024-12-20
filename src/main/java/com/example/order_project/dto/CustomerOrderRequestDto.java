package com.example.order_project.dto;

import com.example.order_project.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderRequestDto {

    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDate orderDate;

}
