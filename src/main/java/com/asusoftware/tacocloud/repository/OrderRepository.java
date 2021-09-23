package com.asusoftware.tacocloud.repository;

import com.asusoftware.tacocloud.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    // Possiamo create metodi custom per cercare qualcosa specifico
    List<Order> findByDeliveryZip(String deliveryZip);
    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
