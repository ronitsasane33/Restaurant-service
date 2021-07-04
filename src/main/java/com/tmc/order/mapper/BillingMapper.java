package com.tmc.order.mapper;

import com.tmc.order.dto.BillingDto;
import com.tmc.order.model.entity.Billing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PaymentMapper.class })
public interface BillingMapper {
    BillingDto toBillingDto(Billing billing);
    Billing toBilling(BillingDto billingDto);
}
