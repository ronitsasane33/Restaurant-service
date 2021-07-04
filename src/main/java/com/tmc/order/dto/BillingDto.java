package com.tmc.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDto {
    private String billingId;
    private Double total;
    private Double tax;
    private List<PaymentDto> payments;
}
