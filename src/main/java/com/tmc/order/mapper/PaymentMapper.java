package com.tmc.order.mapper;

import com.tmc.order.dto.PaymentDto;
import com.tmc.order.model.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BillingMapper.class})
public interface PaymentMapper {
    PaymentDto toPaymentDto(Payment payment);
    Payment toPayment(PaymentDto paymentDto);
}
