package com.example.order_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class OrderProjectException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("Order Project Internal Server Error");

        return pb;
    }
}
