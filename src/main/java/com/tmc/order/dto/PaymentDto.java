package com.tmc.order.dto;

import com.tmc.order.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String paymentId;
    private String confirmationNumber;
    private Double paymentAmount;
    private PaymentType paymentType;
}
