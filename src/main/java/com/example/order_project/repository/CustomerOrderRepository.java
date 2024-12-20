package com.example.order_project.repository;

import com.example.order_project.entity.CustomerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerOrderRepository extends MongoRepository<CustomerOrder, String> {
}
