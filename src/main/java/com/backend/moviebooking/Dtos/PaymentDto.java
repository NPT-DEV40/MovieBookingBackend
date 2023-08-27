package com.backend.moviebooking.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentDto implements Serializable {
    private String status;
    private String orderInfo;
    private String totalPrice;
    private String paymentTime;
    private String transactionId;

}
