package com.tmc.reservation.model;

import com.tmc.reservation.model.enums.TableStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RESTAURANT_TABLE")
public class RestaurantTable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "table_id")
    private String tableId;

    @Column(name = "table_numer")
    private int tableNumber;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "tableStatus")
    private TableStatus tableStatus;
}
