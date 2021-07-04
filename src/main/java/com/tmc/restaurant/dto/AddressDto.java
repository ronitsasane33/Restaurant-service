package com.tmc.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
}
