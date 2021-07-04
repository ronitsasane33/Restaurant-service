package com.tmc.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "restaurant_id")
    private String restaurantId;

    @Column(name = "restaurant_name", unique = true)
    private String restaurantName;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="address_id", referencedColumnName = "address_id")
    private Address address;

}
