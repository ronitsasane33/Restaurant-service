package com.tmc.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MENU")
public class Menu {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "menuType")
    private String menuType;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<FoodItem> foodItems;

}
