package com.tmc.order.model.entity;

import com.tmc.order.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_confirmation_number")
    private String confirmationNumber;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "payment_type")
    private PaymentType paymentType;

}
