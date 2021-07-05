package com.tmc.restaurant.entity;

import com.tmc.restaurant.entity.enums.FoodItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FOOD_ITEM")
public class FoodItem {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "food_id")
    private String foodItemId;

    @Column(name = "food_name")
    private String foodItemName;

    @Column(name = "food_price")
    private int foodItemPrice;

    @Column(name = "food_description")
    private String foodItemDescription;

    @Column(name = "food_status")
    private FoodItemStatus foodItemStatus;
}
