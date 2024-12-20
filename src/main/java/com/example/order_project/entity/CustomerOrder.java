package com.example.order_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "customerOrder")
public class CustomerOrder {

    @Id
    private String id;

    private LocalDate orderDate;

    private List<OrderItem> orderItems = new ArrayList<>();

    private BigDecimal total;


    public BigDecimal getTotal() {
        return orderItems.stream().map(orderItem -> orderItem.getProduct()
                .getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderItem> getOrderItems() {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        return orderItems;
    }

}
