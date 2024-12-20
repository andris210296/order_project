package com.example.order_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderResponseDto {

    private String id;
    private LocalDate orderDate;
    private List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
    private BigDecimal total;

}
