package com.tmc.order.model.entity;

import com.tmc.order.model.enums.OrderStatus;
import com.tmc.restaurant.entity.FoodItem;
import com.tmc.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<FoodItem> foodItems;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Billing billing;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Restaurant restaurant;

}
