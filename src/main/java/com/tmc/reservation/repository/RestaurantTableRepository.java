package com.tmc.reservation.repository;

import com.tmc.reservation.model.RestaurantTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, String> {

    @Query("SELECT tbl FROM RestaurantTable tbl  WHERE tbl.tableId not in (select booking.restaurantTable.tableId FROM Booking booking where booking.bookingStartTime between :startTime AND :endTime)")
    List<RestaurantTable> getAvailableTables(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
   }
