package com.tmc.order.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BILLING")
public class Billing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "billing_id")
    private String billingId;

    @Column(name = "billing_total")
    private Double total;

    @Column(name = "billing_tax")
    private Double tax;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Payment> payments;

}
